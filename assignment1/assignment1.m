clear all, clc;

% Read features from file
features = dlmread('features.txt');
% Read targets from file
targets_ind = dlmread('targets.txt');
targets_vec = full(ind2vec(targets_ind', 7));

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

%Initialize variables
inputs = 10;
hidden_neurons = 8;
outputs = 7;
treshold = 0.2;
temp_value_hidden_layer = 0;
temp_value_output_layer = 0;
learning_rate = 0.1;

%Initialize weights
weights_input_hidden = rand(hidden_neurons, inputs);
weights_hidden_output = rand(outputs, hidden_neurons);
hidden_layer = zeros(hidden_neurons, 1);
output_layer = zeros(outputs, 1);

% For every product
for index = 1:size(features_train, 1)
    %Calculate the values of the hidden layer
    for i = 1:hidden_neurons
        for j = 1:inputs
            temp_value_hidden_layer = temp_value_hidden_layer + weights_input_hidden(i, j) * features_train(index, j) - treshold;
        end

        % Sigmoid
        current_value = 1 / (1 + exp(-temp_value_hidden_layer));
        hidden_layer(i, 1) = current_value;
        temp_value_hidden_layer = 0;
    end

    % Calculate the values of the outputs
    for i = 1:outputs
        for j = 1:hidden_neurons
            temp_value_output_layer = temp_value_output_layer + weights_hidden_output(i, j) * hidden_layer(j, 1) - treshold;
        end

        % Sigmoid function
        current_value = 1 / (1 + exp(-temp_value_output_layer));
        output_layer(i, 1) = current_value;
        temp_value_output_layer = 0;
    end

    error_total = 0;
    error_deriv = zeros(7, 1);
    % For every output in outputs, calulate error and adjust weight
    for k = 1:outputs
        error = targets_train(1, index) - output_layer(k, 1);
        error_total = error_total + error;
        error_deriv(k, 1) = output_layer(k, 1) * (1 - output_layer(k, 1)) * error;

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

end
