<template>
  <img src="@/assets/hello.png" alt="hello.png">
  <h3>Good to see you here!</h3>
  <h1>{{char}}{{fullName}}>></h1>
</template>

<script>
import axios from "axios";

export default {
  name: 'HelloWorld',
  data(){
    return {
      fullName: '',
      char: '<<'
    }
  },
  mounted() {
    axios.get("http://localhost:8080/find/1",)
        .then((response) =>{
          console.log(response.data.firstname)
          this.fullName = response.data.firstname + ' ' + response.data.lastname
        })

  },
}
</script>

<style scoped>

h1{
  color: #2c3e50;
}
</style>
