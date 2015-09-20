% Read the unknown file and save it in a variable
features_unknown = dlmread('unknown.txt');

% Calculate the targets for the unknown
targets_unknown_vec = weights_hidden_output * weights_input_hidden * features_unknown';
% Transform the target from a vector to an index
targets_unknown = vec2ind(targets_unknown_vec);

% Parse the targets as a txt file
dlmwrite('targets_unknown.txt', targets_unknown);