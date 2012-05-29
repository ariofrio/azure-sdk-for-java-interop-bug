echo "Installing dependencies (Ruby, Java) and compiling (Java)..."
cd ruby
(bundle --version) -or (gem install bundler)
bundle install
cd ../java
mvn package

echo "Running test..."
cd ../ruby
bundle exec ruby send.rb
echo "Waiting 1 second..."
Start-Sleep -s 1
cd ../java
java -cp 'target\classes;target\dependency\*' Receiver
cd ..
