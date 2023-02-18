package tech.tenamen.musicplayer.core.searcher;

import java.util.ArrayList;
import java.util.List;

import tech.tenamen.musicplayer.core.helper.WebHelper;
import tech.tenamen.musicplayer.core.player.SoundProvider;

public abstract class SearchEngine <T extends SoundProvider> {
    
    protected int searchIndex;
    protected String lastSearchedTitle, encodedTitle;

    private final List<T> searchResultList;

    protected SearchEngine() {
        this.searchResultList = new ArrayList<>();
    }

    public final void search(final String title) {
        this.searchResultList.clear();
        this.lastSearchedTitle = title;
        this.encodedTitle = WebHelper.encodeString(title);
        this.onSearch();
    }

    public final void searchNext() {
        this.searchResultList.clear();
        this.searchIndex++;
        this.onSearch();
    }

    protected abstract void onSearch();
    protected abstract int getNumberPerPage();

    public final List<T> getSearchList() {
        return this.searchResultList;
    }

    public final void addResult(final T result) {
        this.searchResultList.add(result);
    }

    public final T getResult(final int index) {
        return this.searchResultList.get(index);
    }

    public final int getResultSize() {
        return this.searchResultList.size();
    }
}
