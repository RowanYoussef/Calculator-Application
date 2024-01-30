<template>
    <div class="calculator">
    <div class="output">{{ curr }}</div>
    <input type="button" value="%" @click="percentage" class="operation">
    <input type="button" value="CE" @click="clear" class="operation">
    <input type="button" value="C" @click="clear" class="operation">
    <input type="button" value="Del" @click="deleted"  class="operation">
    <input type="button" value="1/x" @click="division" class="operation">
    <input type="button" value="x^2" @click="square" class="operation">
    <input type="button" value="sqrt" @click="root" class="operation">
    <input type="button" value="÷" @click="div" class="operation">
    <input type="button" value="7" @click= "insert('7')" class="child">
    <input type="button" value="8" @click="insert('8')" class="child">
    <input type="button" value="9" @click="insert('9')" class="child">
    <input type="button" value="x" @click="mult" class="operation">
    <input type="button" value="4" @click="insert('4')" class="child">
    <input type="button" value="5" @click="insert('5')" class="child">
    <input type="button" value="6"  @click="insert('6')" class="child">
    <input type="button" value="_" @click="sub" class="operation">
    <input type="button" value="1"  @click="insert('1')" class="child">
    <input type="button" value="2"  @click="insert('2')" class="child">
    <input type="button" value="3"  @click="insert('3')" class="child">
    <input type="button" value="+"  @click="add" class="operation">
    <input type="button" value="+/-" @click="changeSign" class="operation">
    <input type="button" value="0"  @click="insert('0')" class="child">
    <input type="button" value="." @click="fraction" class="child">
    <input type="button" value="=" @click="equal" class="equal">

    </div>
</template>
<script>
import { exportDefaultSpecifier, tsImportEqualsDeclaration } from '@babel/types';
import { subnetMatch } from 'ipaddr.js';


 export default {
 data(){
    return {
        num1: '',
        num2: '',
        curr: '',
        op: '',
        opflag: false,
        result: '',
        end: false,
        negative: '',
        clicked: false,
        unary: '',

    }
    
 },
 methods :{
    insert(x){
        if(this.curr==="E")
        this.clear()
        if(!this.opflag&&!this.clicked&&!this.end){
        this.curr=this.negative+this.curr+x;
        this.negative='';
        }
       else if(this.opflag&&this.clicked&&!this.end){
        this.num1=this.curr;
        this.curr=this.negative+x;
        this.clicked=false;
        this.negative='';
       }
       else if(this.opflag&&!this.clicked&&!this.end){
        this.curr=this.negative+this.curr+x;
        this.negative='';
       }
       else if(this.end){
        this.curr=this.negative+x;
        this.end=false;
        this.negative='';

    }

    },
    add(){
        if(this.curr==="E")
        this.clear();
    else if(!this.clicked){
        if(!this.opflag&&this.curr!==""){
             this.op="addition";
             this.num1=this.curr;
             this.opflag=true;
             this.clicked=true;
        }else if(this.opflag&&this.curr!==""){
            this.num2=this.curr;
            this.performOperation();
            this.op="addition";
            this.result="";
            this.clicked=true;
        }
        this.end=false;
    }

    },
    sub(){
        if(this.curr==="E")
        this.clear();
        else if(this.curr===""||this.clicked){
             this.negative='-';
            }
        else{
        if(!this.opflag&&this.curr!==""){
             this.op="sub";
             this.num1=this.curr;
             this.opflag=true;
             this.clicked=true;
        }else if(this.opflag&&this.curr!==""){
            this.num2=this.curr;
            this.performOperation();
            console.log(this.result)
            this.op="sub";
            this.result="";
            this.clicked=true;
        }
        this.end=false;
    }
    },
    div(){
        if(this.curr==="E")
        this.clear();
    else if(!this.clicked){
        if(!this.opflag&&this.curr!==""){
             this.op="div";
             this.num1=this.curr;
             this.opflag=true;
             this.clicked=true;
        }else if(this.opflag&&this.curr!==""){
            this.num2=this.curr;
            this.performOperation();
            this.op="div";
            this.result="";
            this.clicked=true;
        }
        this.end=false;
    }
    },
    mult(){
        if(this.curr==="E")
        this.clear();
       else if(!this.clicked){
        if(!this.opflag&&this.curr!==""){
             this.op="mult";
             this.num1=this.curr;
             this.opflag=true;
             this.clicked=true;
        }else if(this.opflag&&this.curr!==""){
            this.num2=this.curr;
            this.performOperation();
            this.op="mult";
            this.result="";
            this.clicked=true;
        }
        this.end=false;
    }
    },
    equal(){
    if(this.curr==="E")
     this.clear();
    else{
    if(!this.end&&this.opflag&&this.curr!==""){
     this.num2=this.curr;
     this.performOperation();
     this.op='';
     this.opflag=false;
     this.end=true;
     console.log(this.curr);
    }
   }
    },
    performOperation() {
        fetch('http://localhost:8087/'+this.num1+'/'+this.op+'/'+this.num2, {method: "get"})
      .then(response=> {
        return response.text();
      }).then(data =>{
        this.curr=data
      });
      this.num1='';
    },
    square() {
        if(this.curr!==''&&this.curr!=="E"&&!this.clicked){
        this.unary="square";
        this.performunaryOperation();
        }
    },
    root() {
        if(this.curr!==''&&this.curr!=="E"&&!this.clicked){
        this.unary="root";
        this.performunaryOperation();
        }
    },
    percentage() {
        if(this.curr!==''&&this.curr!=="E"&&!this.clicked){
        this.unary="percentage";
        this.performunaryOperation();
        }
    },
    division() {
        if(this.curr!==''&&this.curr!=="E"&&!this.clicked){
        this.unary="division";
        this.performunaryOperation();
        }
    },
    changeSign() {
        if(this.curr!==''&&this.curr!=="E"){
        this.unary="changeSign";
        this.performunaryOperation();
        }
    },
    performunaryOperation() {
        fetch('http://localhost:8087/'+this.unary+'/'+this.curr, {method: "get"})
      .then(response=> {
        return response.text();
      }).then(data =>{
        this.curr=data
      });
    },
    clear() {
        this.num1= '';
        this.num2= '';
        this.curr= '';
        this.op= '';
        this.opflag= false;
        this.result= '';
        this.end= false;
        this.negative= '';
        this.clicked= false;
        this.unary= '';
    },
    deleted() {
     if(!this.clicked&&!this.end){
        this.curr=this.curr.slice(0,-1);
     }
    },
    fraction() {
      if(!this.clicked&&!this.end){
        if(!this.curr.includes(".")){
            this.curr=this.curr+".";
        }
      }
    },


 },
 
 }
</script>
<style scoped>
.calculator {
    display: grid;
    margin: 0 auto;
    border: 3px solid pink;
    width: 350px;
    height: 500px;
    font-size: 40px;
    grid-template-columns: repeat(4,1fr);
    grid-auto-rows: minmax(75px,auto);
}
.child {
    background-color: palevioletred;
    border: 1px solid pink;
    font-size: 30px;

}
.child:hover {
    background: pink;
}
.output {
    grid-column: 1/5;
    background-color: bisque;
    text-align: right;
    color: black;
    height: 80px;
    
}
.operation {
    font-size: 25px;
}
.equal {
    font-size: 25px;
    background-color: beige;
}
.equal:hover {
    background-color: gainsboro;
}
.operation:hover {
    background-color: beige;
}
</style>
