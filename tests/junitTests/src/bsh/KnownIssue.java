package bsh;

public interface KnownIssue {

	static final boolean SKIP_KOWN_ISSUES = System.getProperties().containsKey("skip_known_issues");

}
