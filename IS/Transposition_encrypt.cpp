#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <cmath>
using namespace std;
void Decrypt(string text, string key);
bool visit(int n, vector <int> visited);

void Encrypt(string text, string key)
{
    double row=ceil(static_cast<double>(text.size()) / key.size());
    int row1=int(row);
    int array1[key.size()];
    int array2[key.size()];
    int final[key.size()];
    vector <int> visited;
    char matrix[row1][key.size()];
    string key1,encrypt;
    // Converting each char to uppercase
    for(int i=0;i<key.size();i++)
    {
        int temp=key[i];
        if(temp>=65 && temp<=90)
        {
            char temp=key[i];
            key1+=temp;
        }
        else{
            char t=key[i];
            t=t-32;
            key1+=t;
        }
    }
    cout<<key1<<endl;
    // Extracting each character of the string(key)
    for(int i=0;i<key1.size();i++)
    {
        int temp;
        temp=key1[i];
        array1[i]=temp;
        cout<<array1[i]<<" ";
    }
    for(int i=0;i<key.size();i++)
    {
        array2[i]=array1[i];
    }
    cout<<endl;
    // Sorting array
    for(int i=0;i<key.size();i++)
    {
        int min=i;;
        for(int j=i+1;j<key.size();j++)
        {
            if(array2[j]<array2[min])
            {
                min=j;
            }
        }
        int temp=array2[i];
        array2[i]=array2[min];
        array2[min]=temp;
        cout<<array2[i]<<" ";
    }
    // Finding index for printing encrypted message
    cout<<endl;
    int k=0;
    for(int i=0;i<key1.size();i++)
    {
        for(int j=0;j<key.size();j++)
        {
            if(array2[i]==array1[j])
            {
                if(visit(j,visited)==0)
                {
                    final[k]=j;
                    visited.push_back(j);
                    cout<<final[k]<<" ";
                    k++;
                    break;
                }
                else{
                    continue;
                }
            }
        }
    }
    // Printing the text in matrix format
    cout<<endl;
    int m=0;
    for(int i=0;i<row1;i++)
    {
        for(int j=0;j<key1.size();j++)
        {
            char at=text[m];
            if(at!='\0')
            {
            matrix[i][j]=text[m];
            cout<<matrix[i][j]<<" ";
            m++;
            }
            else{
                if((j+1)!=key1.size())
                {
                    while(j!=key1.size())
                    {
                        matrix[i][j]=' ';
                        cout<<matrix[i][j]<<" ";
                        j++;
                    }
                }
            }
        }
        cout<<endl;
    }
    cout<<endl;
    //Printing and storing the encrypted text in a string
    for(int i=0;i<key1.size();i++)
    {
        for(int j=0;j<row1;j++)
        {
            char temp=matrix[j][final[i]];
            encrypt+=temp;
        }
    }
    cout<<encrypt;
    cout<<endl<<"Would you like to decrypt the message?(1/0) ";
    int choice;
    cin>>choice;
    if(choice==1)
    {
        Decrypt(encrypt,key1);
    }
    else
    {
        exit(0);
    }
}

void Decrypt(string text, string key)
{
    double row=ceil(static_cast<double>(text.size()) / key.size());
    int row1=int(row);
    int array1[key.size()];
    int array2[key.size()];
    int final[key.size()];
    vector <int> visited;
    char matrix[row1][key.size()];
    string decrypt;
    cout<<key<<endl;
    for(int i=0;i<key.size();i++)
    {
        int temp;
        temp=key[i];
        array1[i]=temp;
        cout<<array1[i]<<" ";
    }
    for(int i=0;i<key.size();i++)
    {
        array2[i]=array1[i];
    }
    cout<<endl;
    // Sorting array
    for(int i=0;i<key.size();i++)
    {
        int min=i;;
        for(int j=i+1;j<key.size();j++)
        {
            if(array2[j]<array2[min])
            {
                min=j;
            }
        }
        int temp=array2[i];
        array2[i]=array2[min];
        array2[min]=temp;
        cout<<array2[i]<<" ";
    }
    // Finding index for printing encrypted message
    cout<<endl;
    int k=0;
    for(int i=0;i<key.size();i++)
    {
        for(int j=0;j<key.size();j++)
        {
            if(array2[i]==array1[j])
            {
                if(visit(j,visited)==0)
                {
                    final[k]=j;
                    visited.push_back(j);
                    cout<<final[k]<<" ";
                    k++;
                    break;
                }
                else
                {
                    continue;
                }
            }
        }
    }
    // Printing the text in matrix format
    cout<<endl;
    int m=0;
    for(int i=0;i<key.size();i++)
    {
        for(int j=0;j<row1;j++)
        {
             matrix[j][final[i]]=text[m];
                m++;
        }
    }
    for(int i=0;i<row1;i++)
    {
        for(int j=0;j<key.size();j++)
        {
            cout<<matrix[i][j]<<" ";
        }
        cout<<endl;
    }
    //Decryption of Text
    cout<<endl;
    for(int i=0;i<row1;i++)
    {
        for(int j=0;j<key.size();j++)
        {
            char temp=matrix[i][j];
            decrypt+=temp;
        }
    }
    cout<<decrypt;
}

bool visit(int n, vector <int> visited)
{
    for(int i=0;i<visited.size();i++)
    {
        if(n==visited[i])
        {
            return 1;
        }
    }
    return 0;
}

int main()
{
    string text,key;
    cout<<"Enter the Key: ";
    cin>>key;
    cin.ignore();
    cout<<"Enter the text to be encrypted: ";
    getline(cin,text);
    Encrypt(text,key);
}