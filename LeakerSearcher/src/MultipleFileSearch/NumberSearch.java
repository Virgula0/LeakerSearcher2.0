package MultipleFileSearch;

import Processes.ProcessSingleFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class NumberSearch extends ProcessSingleFile {

    private String string_to_search;
    private Set<String> check;
    ArrayList<String> total_size = new ArrayList<>();

    public NumberSearch() {

    }

    public NumberSearch(String file_Path, String search) {
        super(file_Path);
        this.string_to_search = search;
    }

    public long getTotalFileSize(String ext, int lowbound, int upbound) throws IOException {
        return super.getFileSizeMultipleNumbered(ext, upbound, lowbound);
    }

    public Set<String> getCheck() {
        return this.check;
    }

    public Set<String> search(boolean noVerbose, String ext, int upbound, int lowbound) throws IOException {
        this.check = super.searchInNumberedFiles(string_to_search, noVerbose, upbound, ext, lowbound);
        return check;
    }
}
