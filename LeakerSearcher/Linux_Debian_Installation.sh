if [[ $EUID -ne 0 ]]; then
   echo "Script needs root privileges to intall LeakerSearcher"
   exit 1
fi
echo "Intalling leaker searcher in the system please wait..."
cp ./out/artifacts/LeakerSearcher_jar/LeakerSearcher.jar /usr/bin/leakersearcher.jar
echo 'java -jar /usr/bin/leakersearcher.jar "$@"' > /usr/bin/lks
chmod +x /usr/bin/lks
chmod +x /usr/bin/leakersearcher.jar
echo "Done"
echo "Enjoy LeakerSearcher by typing lks"

