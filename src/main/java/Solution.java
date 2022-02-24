public class Solution {

    // input fields
    final public RawInputData rawInputData;

    // output fields
    // TODO add fields that shall be computed for the output

    public Solution(RawInputData rawInputData) {
        this.rawInputData = rawInputData;
    }

    public static Solution from(RawInputData rawInputData) {
        return new Solution(rawInputData);
    }

    // TODO compute output fields with some logic

}
