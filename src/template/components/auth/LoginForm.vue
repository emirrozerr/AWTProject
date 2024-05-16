<script setup>
import { ref } from 'vue';
const checkbox = ref(true);
const email = ref()
const password = ref()
const visible = ref(false)
let resMessage = ''
const loginUser = async () => {
  try {
    // Make sure email and password are not empty
    if (!email.value || !password.value) {
        resMessage = 'Email and Password cannot be empty'
      return;
    }

    const response = await fetch('http://localhost:8080/api/auth/login', {
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
      resMessage = 'Failed to login'
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
          <v-label class="font-weight-bold mb-1">{{ resMessage }}</v-label>
            <v-label class="font-weight-bold mb-1">Email</v-label>
            <v-text-field
                v-model="email"
                placeholder="Email address"
                variant="outlined"
                hide-details color="primary"
                prepend-inner-icon="mdi-email-outline"
            />
        </v-col>
        <v-col cols="12">
            <v-label class="font-weight-bold mb-1">Password</v-label>
            <v-text-field
                v-model="password"
                :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
                :type="visible ? 'text' : 'password'"
                placeholder="Enter your password"
                prepend-inner-icon="mdi-lock-outline"
                variant="outlined"
                type="password"
                hide-details color="primary"
                @click:append-inner="visible = !visible"
            />
        </v-col>
        <v-col cols="12" class="pt-0">
            <div class="d-flex flex-wrap align-center ml-n2">
                <v-checkbox v-model="checkbox"  color="primary" hide-details>
                    <template v-slot:label class="text-body-1">Remeber this Device</template>
                </v-checkbox>
                <div class="ml-sm-auto">
                    <NuxtLink
                      to="/"
                      class="text-primary text-decoration-none text-body-1 opacity-1 font-weight-medium"
                    >
                      Forgot Password ?
                    </NuxtLink>
                </div>
            </div>
        </v-col>
        <v-col cols="12" class="pt-0">
          <v-btn @click="loginUser" color="primary" size="large" block   flat>Sign in</v-btn>
        </v-col>
    </v-row>
</template>
