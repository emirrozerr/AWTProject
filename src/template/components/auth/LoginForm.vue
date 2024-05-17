<script setup>
import { ref, reactive } from 'vue'
import { required, email as emailValidator } from '@vuelidate/validators'
import { useVuelidate } from '@vuelidate/core'

const router = useRouter()
const formState = reactive({
  email: '',
  password: '',
  checkbox: true
})

const validationRules = {
  email: { required, emailValidator },
  password: { required }
}

const v$ = useVuelidate(validationRules, formState)

const resMessage = ref('')
const visible = ref(false)

const loginUser = async () => {
  v$.value.$touch()
  if (v$.value.$invalid) {
    resMessage.value = 'Please fill in all fields correctly'
    return
  }

  try {
    const response = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: formState.email,
        password: formState.password
      })
    })

    if (!response.ok) {
      resMessage.value = 'Failed to login'
      throw new Error('Failed to login')
    }

    const data = await response.json()
    console.log(data)
    localStorage.setItem('token', data.accessToken)
    await router.push('/dashboard')

    // Handle successful login, e.g., redirect user
  } catch (error) {
    console.error(error)
  }
}
</script>

<template>
  <v-row class="d-flex mb-3">
    <v-col cols="12">
      <v-label class="font-weight-bold mb-1">{{ resMessage }}</v-label>
      <v-label class="font-weight-bold mb-1">Email</v-label>
      <v-text-field
          v-model="formState.email"
          placeholder="Email address"
          variant="outlined"
          hide-details color="primary"
          prepend-inner-icon="mdi-email-outline"
          :error-messages="v$.email.$errors.map(error => error.$message)"
      />
    </v-col>
    <v-col cols="12">
      <v-label class="font-weight-bold mb-1">Password</v-label>
      <v-text-field
          v-model="formState.password"
          :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
          :type="visible ? 'text' : 'password'"
          placeholder="Enter your password"
          prepend-inner-icon="mdi-lock-outline"
          variant="outlined"
          hide-details color="primary"
          @click:append-inner="visible = !visible"
          :error-messages="v$.password.$errors.map(error => error.$message)"
      />
    </v-col>
    <v-col cols="12" class="pt-0">
      <div class="d-flex flex-wrap align-center ml-n2">
        <v-checkbox v-model="formState.checkbox" color="primary" hide-details>
          <template v-slot:label class="text-body-1">Remember this Device</template>
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
      <v-btn @click="loginUser" color="primary" size="large" block flat>Sign in</v-btn>
    </v-col>
  </v-row>
</template>
