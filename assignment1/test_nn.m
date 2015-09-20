function [ percentage ] = test_nn(weights_hidden_output, weights_input_hidden, features_test, targets_test)
    % Test on the test set
    output = weights_hidden_output * weights_input_hidden * features_test';

    count = 0;
    for i = 1:size(output, 2)
        [m, index] = max(output(1:end, i));
        target_index = vec2ind(targets_test(1:end, i));

        if index == target_index
            count = count + 1;
        end
    end

    percentage = count / size(targets_test, 2) * 100;
end

