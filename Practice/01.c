#include<stdio.h>
#include<string.h>

#define N strlen(gen_poly)

char data[28];
char remainder_value[28];
char gen_poly[10];
int i, j, data_length;

void XOR(){
    for(j = 1;j<N;j++){
        remainder_value[j] = (remainder_value[j]==data[j]) ? '0' : '1';
    }
}

void crc(){
    for(i = 0;i<N;i++){
        remainder_value[i] = data[i];
    }
    do{
        if(remainder_value[i] == '1'){
            XOR();
        }
        for(j = 0;j<N-1;j++){
            remainder_value[j] = remainder_value[j+1]; 
        }
        remainder_value[j] = data[i++]; 
    }while(i<=data_length+N-1);
}

void receiver(){
    printf("\nEnter the received data : ");
    scanf("%s",data);
    printf("\nData received : %s",data);

    crc();

    for(i = 0;(i<N-1) && (remainder_value[i]!='1');i++);
    if(i<N-1){
        printf("\nError detected");
    }
    else{
        printf("\nNo Error detected");
    }
}

int main(){
    printf("Enter data to be transmitted : ");
    scanf("%s",data);
    printf("\nEnter the generating polynomial : ");
    scanf("%s",gen_poly);
    data_length = strlen(data);
    for(i = data_length;i<data_length+N-1;i++){
        data[i] = '0';
    }
    printf("\nData padded with n-1 zeroes : %s", data);

    crc();

    printf("\n CRC or Check Value is : %s",remainder_value);

    for(i = data_length;i<data_length+N-1;i++){
        data[i] = remainder_value[i-data_length];
    }
    printf("\nFinal data to be sent :  %s", data);

    receiver();
    return 0;
}