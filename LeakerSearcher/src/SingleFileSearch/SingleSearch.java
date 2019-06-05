package SingleFileSearch;
import Processes.ProcessSingleFile;

import java.io.IOException;
import java.util.Set;

public class SingleSearch extends ProcessSingleFile {

    private String string_to_search;
    private Set<String> check;

    public SingleSearch(String file_Path,String search) {
        super(file_Path);
        this.string_to_search = search;
    }

    public long getTotalFileSize()throws IOException{
        return super.getFileSize();
    }

    public Set<String> getCheck(){
        return this.check;
    }

    public Set<String> search(boolean noVerbose)throws IOException {
        this.check = super.searchInAFile(string_to_search,noVerbose);
        return check;
    }

}
