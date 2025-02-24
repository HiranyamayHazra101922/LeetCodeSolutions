class Solution {
public:
    bool isPalindrome(int x) {
          long int t=x;
     long int r=0;

    while (t)
    {
        r = r * 10 + t % 10;
        t /= 10;
    }
    return(x >= 0 && r == x);
    }
};