
import { useVuelidate } from '@vuelidate/core'
import * as validators from '@vuelidate/validators'

export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.provide('vuelidate', useVuelidate)
    nuxtApp.provide('validators', validators)
});

