clear all;
clc, clf, close;

%Initialize variables
inputs = 10;
epochs = 300;
hidden_neurons = 12;
outputs = 7;
treshold = 0.2;
temp_value_hidden_layer = 0;
temp_value_output_layer = 0;
learning_rate = 0.1;

%% Function starts here %%

% Read features from file
features = dlmread('features.txt');
% Read targets from file
targets = dlmread('targets.txt');
targets_vec = full(ind2vec(targets', 7));

% Define percentage of set used for 
% [training, testing, validating].
parts = [0.70, 0.15, 0.15];
divider1 = uint32(parts(1) * size(features, 1));
divider2 = uint32((parts(1) + parts(2)) * size(features, 1));

% Split set
features_train = features(1:divider1, 1:end);
features_test = features(divider1 + 1:divider2, 1:end);
features_validate = features(divider2 + 1:end, 1:end);

targets_train = targets_vec(1:end, 1:divider1);
targets_test = targets_vec(1:end, divider1 + 1:divider2);
targets_validate = targets_vec(1:end, divider2 + 1:end);
targets_ind = targets(1:divider1, 1:end);

% treshold_hidden = rand(hidden_neurons);
% treshold_output = rand(outputs);

%Initialize weights
weights_input_hidden = rand(hidden_neurons, inputs);
weights_hidden_output = rand(outputs, hidden_neurons);
weights_input_hidden_epoch = zeros(hidden_neurons, inputs, epochs);
weights_hidden_output_epoch = zeros(outputs, hidden_neurons, epochs);
hidden_layer = zeros(hidden_neurons, 1);
output_layer = zeros(outputs, 1);

error = zeros(size(targets_train, 1), outputs);
error_total = zeros(size(targets_train, 1), 1);
targets_calc = zeros(size(targets_train, 1), 1);

for e = 1:epochs
    % For every product
    for index = 1:size(features_train, 1)

        %Calculate the values of the hidden layer
        for i = 1:hidden_neurons
            for j = 1:inputs
                % Calculate the value for the hidden layer using the inputs
                % and the weights
                temp_value_hidden_layer = temp_value_hidden_layer + weights_input_hidden(i, j) * features_train(index, j) - treshold;
            end

            % Using the Sigmoid function on the hidden layer values
            current_value = 1 / (1 + exp(-temp_value_hidden_layer));
            % Set the hidden layer values in the matrix for these values
            hidden_layer(i, 1) = current_value;
            % Reset the temporary hidden layer value variable
            temp_value_hidden_layer = 0;
        end

        % Calculate the values of the outputs
        for i = 1:outputs
            for j = 1:hidden_neurons
                % Calculate the value for the output layer using the
                % hidden layer values and the weights
                temp_value_output_layer = temp_value_output_layer + weights_hidden_output(i, j) * hidden_layer(j, 1) - treshold;
            end

            % Using the Sigmoid function on the output layer values
            current_value = 1 / (1 + exp(-temp_value_output_layer));
            % Set the output layer values in the matrix for these values
            output_layer(i, 1) = current_value;
            % Reset the temporary output layer variable
            temp_value_output_layer = 0;
        end

        % Set the beginning of the error_total matrix at the current index
        % to zero
        error_total(index, 1) = 0;
        % Reset the error derivatives matrix every single loop
        error_deriv = zeros(7, 1);
        % For every output in outputs, calulate error and adjust weight
        for k = 1:outputs
            error(index, k) = targets_train(k, index) - output_layer(k, 1);
            error_total(index, 1) = error_total(index, 1) + error(index, 1);
            error_deriv(k, 1) = output_layer(k, 1) * (1 - output_layer(k, 1)) * error(index, k);

            for j = 1:hidden_neurons
                weight_delta = learning_rate * hidden_layer(j, 1) * error_deriv;
                weights_hidden_output(k, j) = weights_hidden_output(k, j) + weight_delta(k, 1);
            end
        end

        hidden_error_deriv = zeros(hidden_neurons, 1);
        % For every hidden neuron, adjust weight for sum(output error)
        for j = 1:hidden_neurons
            sum_deriv_weight = 0;

            for k = 1:outputs
               sum_deriv_weight = sum_deriv_weight + error_deriv(k, 1) * weights_hidden_output(k, j);
            end

            hidden_error_deriv(j, 1) = hidden_layer(j, 1) * (1 - hidden_layer(j, 1)) * sum_deriv_weight;
        end

        for i = 1:inputs
           for j = 1 : hidden_neurons
              weights_input_hidden(j, i) = weights_input_hidden(j, i) + learning_rate * features_train(index, i) * hidden_error_deriv(j, 1);
           end
        end

        [m, ind] = max(output_layer);
        targets_calc(index, 1) = ind;

    end

%     fprintf('Epoch %d - ', e);
    count = 0;
    for i = 1:size(targets_ind, 1)
        if targets_ind(i, 1) == targets_calc(i, 1)
            count = count + 1;
        end
    end

    MSE = 0;

    for i = 1:size(error, 1)
        MSE = MSE + error(i,1) * error(i,1);
    end

    MSE = MSE / 5498;

    p = count / size(targets_train, 2) * 100;
%     fprintf('%d samples, %d correct (%f%%) MSE %f\n', size(targets_train, 2), count, p, MSE);

    result(e) = MSE;
    
    % keep track of al the last weights
    weights_hidden_output_epoch(:,:,e) = weights_hidden_output;
    weights_input_hidden_epoch(:,:,e) = weights_input_hidden;
    
    % validate the new weights.
    validation;
    
    %when new MSE is greater than the last MSE then the old weights will be
    %used
    if e > 1
        if MSE_v(e) > MSE_v(e-1)
            weights_hidden_output =  weights_hidden_output_epoch(:,:,e-1);
            weights_input_hidden = weights_input_hidden_epoch(:,:,e-1);
            %break;
        end
    end
    
    % progress
    if mod( e, 10) == 0
    
        disp(e);
        
    end
end

figure(1)
plot(result)
xlabel('epoch');
ylabel('Mean Square Error');
title('Neural network');


test;