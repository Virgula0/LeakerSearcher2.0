echo "Intalling leaker searcher in the system please wait..."
cp ./out/artifacts/LeakerSearcher_jar/LeakerSearcher.jar /usr/local/bin/leakersearcher.jar
echo 'java -jar /usr/local/bin/leakersearcher.jar "$@"' > /usr/local/bin/lks
chmod +x /usr/local/bin/lks
chmod +x /usr/local/bin/leakersearcher.jar
echo "Done"
echo "Enjoy LeakerSearcher by typing lks"
