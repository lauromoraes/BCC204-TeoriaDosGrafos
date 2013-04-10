package extras;

import java.util.Comparator;

public class EntryComparator implements Comparator<Entry> {
	private int evaluation_type;
	public EntryComparator() {}
	public EntryComparator(int evaluation_type) {
		this.evaluation_type = evaluation_type;
	}
	@Override
	public int compare(Entry entry1, Entry entry2) {
		return Double.compare(entry1.evaluation(this.evaluation_type), entry2.evaluation(this.evaluation_type));
	}
	public int compare(Entry entry1, Entry entry2, int evaluation_type) {
		return Double.compare(entry1.evaluation(evaluation_type), entry2.evaluation(evaluation_type));
	}
	public void setEvaluationType(int evaluation_type) {
		this.evaluation_type = evaluation_type;
	}
}
