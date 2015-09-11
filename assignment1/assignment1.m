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

%Initialize variables
inputs = 10;
hidden_neurons = 8;
outputs = 7;
treshold = 0.2;
temp_value_hidden_layer = 0;
temp_value_output_layer = 0;

%Initialize weights
init_weights_input_hidden = rand(hidden_neurons, inputs);
init_weights_hidden_output = rand(outputs, hidden_neurons);
hidden_layer = zeros(hidden_neurons, 1);
output_layer = zeros(outputs, 1);

%Calculate the values of the hidden layer
for i = 1:hidden_neurons
    for j = 1:inputs
        temp_value_hidden_layer = temp_value_hidden_layer + init_weights_input_hidden(i, j) * features_test(1, j);
    end
    hidden_layer(i, 1) = temp_value_hidden_layer;
    temp_value_hidden_layer = 0;
end

% Calculate the values of the outputs
for i = 1:outputs
    for j = 1:hidden_neurons
        temp_value_output_layer = temp_value_output_layer + init_weights_hidden_output(i, j) * hidden_layer(j, 1) - treshold;
    end
    % Sigmoid function
    current_value = 1 / (1 + exp(-temp_value_output_layer));
    output_layer(i, 1) = temp_value_output_layer;
    temp_value_output_layer = 0;
end