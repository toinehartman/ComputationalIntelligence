% Test on the test set
output = weights_hidden_output * weights_input_hidden * features_test';

count = 0;
for i = 1:size(output, 2)
    [m, index] = max(output(1:end, i));
%     fprintf('%f, %d ', m, index);
    target_index = vec2ind(targets_test(1:end, i));
%     fprintf('%d\n', target_index);

    if index == target_index
        count = count + 1;
    end
end

percentage = count / size(targets_test, 2) * 100;
fprintf('%d samples, %d correct (%f%%)\n', size(output, 2), count, percentage);
