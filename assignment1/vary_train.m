% Save the log file to a variable
log_file = fopen('train_results.txt', 'w');

% Print all the statistics for all different inputs
for epochs = 10:8:34
   for hidden_neurons = 7:4:30
      for threshold = 0:0.1:0.7 
          for learning_rate = 0.05:0.01:0.1
              train;
              p = test_nn(weights_hidden_output, weights_input_hidden, features_test, targets_test);
              fprintf(log_file, '%f%% e = %d, h_n = %d, thres = %f, l_r = %f\n', p, epochs, hidden_neurons, threshold, learning_rate);
              fprintf('%f%% e = %d, h_n = %d, thres = %f, l_r = %f\n', p, epochs, hidden_neurons, threshold, learning_rate);
          end
      end
   end
end