<script setup lang="ts">
import Shadow from "@/components/style-components/shadow/Shadow.vue";
definePageMeta({
  middleware: 'auth'
})
</script>

<template>
    <v-row>
        <v-col cols="12">
            <Shadow/>
        </v-col>
    </v-row>
</template>
