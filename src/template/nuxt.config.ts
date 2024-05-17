// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  plugins: [
    '~/plugins/vuelidate.ts'
  ],
  ssr: false,
  typescript: {
    shim: false
  },
  build: {
    transpile: ["vuetify"],
  },
  vite: {
    define: {
      "process.env.DEBUG": false,
    },
  },
  nitro: {
    serveStatic: true,
  },
  devServerHandlers: [],
  hooks: {
  },

})


