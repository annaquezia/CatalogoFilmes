package example.model;

public class MovieComparator {
    public MovieComparator() {}

    public int forType(ComparatorType type, Movie a, Movie b) {
        switch (type) {
            case TITLE:
                int movieTitle = a.getTitle().compareToIgnoreCase(b.getTitle());
                if (movieTitle != 0) {
                    return movieTitle;
                } else
                    return Integer.compare(a.getYear(), b.getYear());
            case YEAR:
                int movieYear = Integer.compare(a.getYear(), b.getYear());
                if (movieYear != 0) {
                    return movieYear;
                } else
                    return a.getTitle().compareToIgnoreCase(b.getTitle());
            case TIME:
                int movieTime = Integer.compare(a.getTime(), b.getTime());
                if (movieTime != 0) {
                    return movieTime;
                } else
                    return a.getTitle().compareToIgnoreCase(b.getTitle());
        }
        return 0;
    }
}
