features_unknown = dlmread('unknown.txt');

targets_unknown_vec = weights_hidden_output * weights_input_hidden * features_unknown';
targets_unknown = vec2ind(targets_unknown_vec);

dlmwrite('targets_unknown.txt', targets_unknown);