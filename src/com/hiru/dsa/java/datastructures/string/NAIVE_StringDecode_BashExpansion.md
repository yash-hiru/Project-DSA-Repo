```java
/**
 Just compiled but implemented End to End in 35 mins.
 Used String tips 
 */
public class BashExpansion {

    //////////////////////////////////////////////////////////////////////////////
    String getExpandedString(String input, int size) {
        // STEP 1 ====> Validity Check using stack.
        // Push { and pop it for each } ignore other characters.
        // At the end both string and stack should be empty
        if (isValid(input, size)) {
            // STEP 2 ====> Divide and process parts to results list of lists
            // Expand each pair of brackets separately
            // At the end both string and stack should be empty'
            ArrayList<ArrayList<String>> parts = new ArrayList<ArrayList<String>>();
            int i = 0;
            String result;
            while (true) {
                String ch = input.substring(i, i + 1);
                if (ch != "{") {
                    // Process continuous string of non special part
                    String part = input.substring(i, input.indexOf('{'));//TODO--safeguard
                    parts.add(Collections.singletonList(part));
                    i += input.indexOf('{'); // Change index
                } else {
                    // Special part(range)
                    input = input.substring(i, input.length()); // reduce string
                    int i1 = input.indexOf('{');
                    int i2 = input.indexOf('}');
                    String range = input.substring(i1 + 1, i2); // Without { and }
                    results.add(expand(range));
                    i = i2 + 1; // Advance to next character to "}"
                }
                if (i >= size)
                    break;

            }

            // Step3 ===> Generate final string
            ArrayList<String> result1 = results.get(0);
            List<String> result2 = results.get(1);
            String solution = ""; //--########## THIS IS OUTPUT
            for (int ss = 2; ss < results.size(); ss++) {
                ArrayList<String> result1 = new ArrayList<String>();
                ArrayList<String> result2 = new ArrayList<String>();
                result1 = results.get(ss);

                if (ss + 1 < size)
                    result2 = results.get(ss + 1);
                // Merge 2 lists along with previously processed prefix
                solution = merge(solution, prevString, currentString);
            }
            return solution;
        }
    }


    //////////////////////////////////////////////////////////////////////////////
    ArrayList<String> expand(String range) {
        ArrayList<String> res = new ArrayList<String>();

        if (range == "a--z") {
            for (int i = 0; i < 26; i++) {
                res.add("" + ('a' + i));
            }

        } else if (range == "a--z") {
            res.add("" + ('A' + i));

        } else if (range == "1,2,3") {
            res.add("1");
            res.add("2");
            res.add("3");

        } else {
            res = decodeSpecialX(range); // SCOPE for future expansion in same framework
        }
        return res;

    }


    //////////////////////////////////////////////////////////////////////////////
    String merge(String prefix, ArrayList<String> list1, ArrayList<String> list1) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                prefix += list1.get(i) + list2.get(j);
            }
        }
        return prefix;
    }


}
```
