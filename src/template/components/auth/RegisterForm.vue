<script setup lang="ts">
import { ref } from 'vue';
const checkbox = ref(true);
const email = ref()
const password = ref()
const name = ref()
const registerUser = async () => {
  try {
    // Make sure email and password are not empty
    if (!email.value || !password.value) {
      return;
    }

    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: email.value,
        password: password.value
      })
    });

    if (!response.ok) {
      throw new Error('Failed to login');
    }

    // Handle response accordingly, maybe redirect or set token in local storage
    const data = await response.json();
    console.log(data);

  } catch (error) {
    // Handle error, maybe show error message
    console.error(error);
  }
};
</script>

<template>
    <v-row class="d-flex mb-3">
        <v-col cols="12">

            <v-label class="font-weight-bold mb-1">Name</v-label>
            <v-text-field v-model="name" variant="outlined" hide-details color="primary"></v-text-field>
        </v-col>
        <v-col cols="12">
            <v-label class="font-weight-bold mb-1">Email Address</v-label>
            <v-text-field v-model="email" variant="outlined" type="email" hide-details color="primary"></v-text-field>
        </v-col>
        <v-col cols="12">
            <v-label class="font-weight-bold mb-1">Password</v-label>
            <v-text-field v-model="password" variant="outlined" type="password"  hide-details color="primary"></v-text-field>
        </v-col>
        <v-col cols="12" >
            <v-btn @click="registerUser" color="primary" size="large" block   flat>Sign up</v-btn>
        </v-col>
    </v-row>
</template>
