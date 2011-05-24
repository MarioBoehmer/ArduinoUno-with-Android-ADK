#include <Max3421e.h>
#include <Usb.h>

#include <Wire.h>

#include <AndroidAccessory.h>

AndroidAccessory acc("Google, Inc.",
"DemoKit",
"DemoKit Arduino Board",
"1.0",
"http://www.android.com",
"0000000012345678");
void setup()
{
  Serial.begin(9600);
  Serial.print("\r\nStart");
  acc.powerOn();
}

void loop()
{
  byte msg[4];

  if (acc.isConnected()) {
    int len = acc.read(msg, sizeof(msg), 1);


    if (len > 0) {
      //command to switch a digital port
      if (msg[0] == 0x1) {
        //which digital port to switch
        if (msg[1] == 0x2) {
          pinMode(2, OUTPUT);
          //switch to outputModeHighLow
          if(msg[2] == 0x0) {
            if(msg[3] == 0x0) {
              digitalWrite(2, LOW);
            } 
            else {
              digitalWrite(2, HIGH);
            }
          }
        }
        else if (msg[1] == 0x3) {
          pinMode(3, OUTPUT);
          if(msg[2] == 0x0) {
            if(msg[3] == 0x0) {
              digitalWrite(3, LOW);
            } 
            else {
              digitalWrite(3, HIGH);
            }
          } 
          else if(msg[2] == 0x1) {
            byte tempvalue = msg[3];
            int value = 0;
            if(tempvalue < 0) {
              value = tempvalue + 256;
            } 
            else {
              value = tempvalue;
            }
            analogWrite(3, value);
          }
        }
        else if (msg[1] == 0x4) {
          pinMode(4, OUTPUT);
          if(msg[2] == 0x0) {
            if(msg[3] == 0x0) {
              digitalWrite(4, LOW);
            } 
            else {
              digitalWrite(4, HIGH);
            }
          }
        }
        else if (msg[1] == 0x5) {
          pinMode(5, OUTPUT);
          if(msg[2] == 0x0) {
            if(msg[3] == 0x0) {
              digitalWrite(5, LOW);
            } 
            else {
              digitalWrite(5, HIGH);
            }
          } 
          else if(msg[2] == 0x1) {
            byte tempvalue = msg[3];
            int value = 0;
            if(tempvalue < 0) {
              value = tempvalue + 256;
            } 
            else {
              value = tempvalue;
            }
            analogWrite(5, value);
          }
        }
        else if (msg[1] == 0x6) {
          pinMode(6, OUTPUT);
          if(msg[2] == 0x0) {
            if(msg[3] == 0x0) {
              digitalWrite(6, LOW);
            } 
            else {
              digitalWrite(6, HIGH);
            }
          } 
          else if(msg[2] == 0x1) {
            byte tempvalue = msg[3];
            int value = 0;
            if(tempvalue < 0) {
              value = tempvalue + 256;
            } 
            else {
              value = tempvalue;
            }
            analogWrite(6, value);
          }
        }
        else if (msg[1] == 0x7) {
          pinMode(7, OUTPUT);
          if(msg[2] == 0x0) {
            if(msg[3] == 0x0) {
              digitalWrite(7, LOW);
            } 
            else {
              digitalWrite(7, HIGH);
            }
          }
        }
      }
    }
  } 
  else {
    //reset all pins to defaults if not connected   
    pinMode(2, INPUT);
    pinMode(3, INPUT);
    pinMode(4, INPUT);
    pinMode(5, INPUT);
    pinMode(6, INPUT);
    pinMode(7, INPUT);				
  }
  delay(10);
}





