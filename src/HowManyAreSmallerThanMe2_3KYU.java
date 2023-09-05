public class HowManyAreSmallerThanMe2_3KYU {

//    DESCRIPTION:
//    This is a hard version of How many are smaller than me?. If you have troubles solving this one, have a look at the easier kata first.
//
//    Write
//
//    function smaller(arr)
//    that given an array arr, you have to return the amount of numbers that are smaller than arr[i] to the right.
//
//    For example:
//
//    smaller([5, 4, 3, 2, 1]) === [4, 3, 2, 1, 0]
//    smaller([1, 2, 0]) === [1, 1, 0]


    private static int[] tree;

    public static int[] smaller(int[] nums) {
        tree = new int[400002];
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = query(-10000, nums[i] - 1);
            add(nums[i]);
        }
        return result;
    }

    public static void add (int num) {
        num += 30001;
        tree[num] += 1;
        for (int i = num / 2; i != 0; i /= 2) {
            tree[i] += 1;
        }
    }

    public static int query (int left, int right) {
        left += 30001;
        right += 30001;

        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left];
                left += 1;
            }
            if (right % 2 == 0) {
                sum += tree[right];
                right -= 1;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

}
