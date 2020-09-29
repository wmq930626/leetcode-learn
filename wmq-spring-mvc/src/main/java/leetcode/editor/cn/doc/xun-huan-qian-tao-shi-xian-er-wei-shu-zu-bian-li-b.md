循环嵌套实现二维数组遍历

### 代码

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
int[] aa={0,0};

        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target&& i!=j) {
                   aa = new int[] {i, j};
                    System.out.println(aa[0] + "," + aa[1]);
                }
            }

        }
        return aa;

    }
}
```