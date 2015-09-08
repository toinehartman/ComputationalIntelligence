% Read features from file
features = dlmread('features.txt');
% Read targets from file
targets = dlmread('targets.txt');

% Define percentage of set used for 
% [training, testing, validating].
parts = [0.70, 0.15, 0.15];
divider1 = uint32(parts(1) * size(features, 1));
divider2 = uint32((parts(1) + parts(2)) * size(features, 1));

% Split set
features_train = features(1:divider1, 1:end);
features_test = features(divider1 + 1:divider2, 1:end);
features_validate = features(divider2 + 1:end, 1:end);