package netdb.courses.softwarestudio.recommender.analysis;

/**
 * Normalizes token text to lower case.
 */
public final class LowerCaseFilter extends TokenFilter {

	public LowerCaseFilter(TokenStream input) {
		super(input);
	}
	
	/**
	 * FIXME Current implementation causes excessive creation of String objects.
	 * Avoided this using char[].
	 */
	@SuppressWarnings("static-access")
	@Override
	public final boolean incrementToken() throws Exception {
		if(input.incrementToken()) 
		{
			char[] lowerTerm = token.getTerm().toCharArray();
			//Character[] lowerTerm = token.getTerm().toCharArray();
			
			for(int i=0 ; i<lowerTerm.length ; i++)
			{
				Character temp = lowerTerm[i];
				lowerTerm[i] = temp.toLowerCase(lowerTerm[i]);
			}
				
			//String lowerTerm = token.getTerm().toLowerCase();
			token.setTermBuffer(lowerTerm, 0, lowerTerm.length);
			return true;
		} else
			return false;
	}
}
