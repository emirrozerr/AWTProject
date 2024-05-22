
import '@mdi/font/css/materialdesignicons.css'

import 'vuetify/styles'
import { createVuetify } from 'vuetify'

import VueApexCharts from 'vue3-apexcharts';
import VueTablerIcons from 'vue-tabler-icons';
import '@/scss/style.scss';

import {
  PurpleTheme,
} from "@/theme/LightTheme";

export default defineNuxtPlugin((nuxtApp) => {
  const vuetify = createVuetify({
    theme: {
      defaultTheme: "PurpleTheme",
      themes: {
        PurpleTheme,
      },
    },
  });

  nuxtApp.vueApp.use(vuetify);
  nuxtApp.vueApp.use(VueApexCharts);
  nuxtApp.vueApp.use(VueTablerIcons);
});

