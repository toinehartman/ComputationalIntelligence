
%Initialize weights
temp_value_hidden_layer_validation = 0;
temp_value_output_layer_validation = 0;
hidden_layer_validation = zeros(hidden_neurons, 1);
output_layer_validation = zeros(outputs, 1);

error_validation = zeros(size(targets_validate, 1), outputs);
error_validation_total = zeros(size(targets_validate, 1), 1);
targets_calc = zeros(size(targets_validate, 1), 1);

    % For every product
    for index = 1:size(features_validate, 1)

        %Calculate the values of the hidden layer
        for i = 1:hidden_neurons
            for j = 1:inputs
                % Calculate the value for the hidden layer using the inputs
                % and the weights
                temp_value_hidden_layer_validation = temp_value_hidden_layer_validation + weights_input_hidden(i, j) * features_validate(index, j) - treshold;
            end

            % Using the Sigmoid function on the hidden layer values
            current_value = 1 / (1 + exp(-temp_value_hidden_layer_validation));
            % Set the hidden layer values in the matrix for these values
            hidden_layer_validation(i, 1) = current_value;
            % Reset the temporary hidden layer value variable
            temp_value_hidden_layer_validation = 0;
        end

        % Calculate the values of the outputs
        for i = 1:outputs
            for j = 1:hidden_neurons
                % Calculate the value for the output layer using the
                % hidden layer values and the weights
                temp_value_output_layer_validation = temp_value_output_layer_validation + weights_hidden_output(i, j) * hidden_layer_validation(j, 1) - treshold;
            end

            % Using the Sigmoid function on the output layer values
            current_value = 1 / (1 + exp(-temp_value_output_layer_validation));
            % Set the output layer values in the matrix for these values
            output_layer_validation(i, 1) = current_value;
            % Reset the temporary output layer variable
            temp_value_output_layer_validation = 0;
        end

        % Set the beginning of the error_validation_total matrix at the current index
        % to zero
        error_validation_total(index, 1) = 0;
        % Reset the error_validation derivatives matrix every single loop
        error_validation_deriv = zeros(7, 1);
        % For every output in outputs, calulate error_validation 
        for k = 1:outputs
            error_validation(index, k) = targets_validate(k, index) - output_layer_validation(k, 1);
            error_validation_total(index, 1) = error_validation_total(index, 1) + error_validation(index, 1);
            error_validation_deriv(k, 1) = output_layer_validation(k, 1) * (1 - output_layer_validation(k, 1)) * error_validation(index, k);
        end

        hidden_error_validation_deriv = zeros(hidden_neurons, 1);
        % For every hidden neuron, adjust weight for sum(output error_validation)
        for j = 1:hidden_neurons
            sum_deriv_weight = 0;

            for k = 1:outputs
               sum_deriv_weight = sum_deriv_weight + error_validation_deriv(k, 1) * weights_hidden_output(k, j);
            end

            hidden_error_validation_deriv(j, 1) = hidden_layer_validation(j, 1) * (1 - hidden_layer_validation(j, 1)) * sum_deriv_weight;
        end

        [m, ind] = max(output_layer_validation);
        targets_calc(index, 1) = ind;

    end

     %fprintf('Epoch %d - ', e);
%     count = 0;
%     for i = 1:size(targets_ind, 1)
%         if targets_ind(i, 1) == targets_calc(i, 1)
%             count = count + 1;
%         end
%     end

    MSE_v(e) = 0;

    for i = 1:size(error_validation, 1)
        MSE_v(e) = MSE_v(e) + error_validation(i,1) * error_validation(i,1);
    end

    MSE_v(e) = MSE_v(e) / 5498;

    p = count / size(targets_validate, 2) * 100;
%     fprintf('%d samples, %d correct (%f%%) MSE_v %f\n', size(targets_train, 2), count, p, MSE_v);

    result_v(e) = MSE_v(e);
   
    %fprintf('Epoch %d - ', MSE_v, '\n');
    


figure(2)
plot(result_v)
xlabel('epoch');
ylabel('Mean Square Error');
title('Validation set performance');
