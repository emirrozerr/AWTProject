<script setup lang="ts">
import AuthLogin from '@/pages/auth/Login.vue';
definePageMeta({
  layout: "blank",
});
const router = useRouter()

onMounted(() => {
  router.push('/auth/login')
})
</script>

<template>
<AuthLogin></AuthLogin>
</template>
