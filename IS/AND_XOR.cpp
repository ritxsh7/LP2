#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    string str="Hello World";
    int arr[str.size()];
    string final_arr[str.size()];
    // Conversion of Each Char of String to Binary
    for(int i=0;i<str.size();i++)
    {
        int temp;
        string final,temp1;
        temp=str[i];
        while(temp>0)
        {
            int var;
            var=temp%2;
            temp1=to_string(var);
            final.append(temp1);
            temp=temp/2;
        }
        final_arr[i]=final;
    }
    cout<<"--------------------------------------------------"<<endl;
    // Reversing each string in  the array of string
    for(int i=0;i<str.size();i++)
    {
        string t=final_arr[i];
        reverse(t.begin(),t.end());
        final_arr[i]=t;
        cout<<final_arr[i]<<endl;
    }
    cout<<"---------------------------------------------------"<<endl;
    // Converting each string of array final_arr to integer and putting it into arr
    for(int i=0;i<str.size();i++)
    {
        int t;
        t=stoi(final_arr[i]);
        arr[i]=t;
        cout<<arr[i]<<endl;
    }
    cout<<"---------------------------------------------------"<<endl;
    //Applying Bitwise AND operations on the integers of array arr
    for(int i=0;i<str.size();i++)
    {
        cout<<"Apllication of AND on each Char of the string: "<<endl;
        int sol=str[i]&127;
        cout<<sol<<endl;
    }cout<<"---------------------------------------------------"<<endl;
    //Applying Bitwise XOR operations on the integers of array arr
    for(int i=0;i<str.size();i++)
    {
        cout<<"Apllication of XOR on each Char of the string: "<<endl;
        int sol=str[i]^127;
        cout<<sol<<endl;
    }
}