<script setup lang="ts">
import {computed} from 'vue';
import {useTheme} from 'vuetify';
import {taskService} from "~/services/taskService";
import type {Task} from "~/types/Task";

const theme = useTheme();
const primary = theme.current.value.colors.primary;
const lightprimary = theme.current.value.colors.lightprimary;
const secondary = theme.current.value.colors.secondary;
const chartOptions = computed(() => {
    return {
        labels: ['todo', 'doing', 'done'],
        chart: {
            type: 'donut',
            fontFamily: `inherit`,
            foreColor: '#a1aab2',
            toolbar: {
                show: false
            }
        },
        colors: [primary, secondary, lightprimary],
        plotOptions: {
            pie: {
                startAngle: 0,
                endAngle: 360,
                donut: {
                    size: '75%',
                    background: 'transparent'
                }
            }
        },
        stroke: {
            show: false
        },

        dataLabels: {
            enabled: false
        },
        legend: {
            show: false
        },
        tooltip: { theme: "light", fillSeriesColor: false },
    };
});
const allTasks = ref<Task[]>([]);
const allTodoTask = ref<Task[]>([]);
const allDoingTask = ref<Task[]>([]);
const allDoneTask = ref<Task[]>([]);

const Chart = ref([50, 40, 25]);
// Use ref for reactive data
const fetchAllTasks = async () => {
  try {
    allTasks.value = await taskService.getAllTasks();
    allTodoTask.value = allTasks.value.filter(task => task.status === 'todo')
    allDoingTask.value = allTasks.value.filter(task => task.status === 'doing')
    allDoneTask.value = allTasks.value.filter(task => task.status === 'done')
    Chart.value = [allTodoTask.value.length,allDoingTask.value.length,allDoneTask.value.length]
    console.log('Fetched Tasks:', allTasks.value);
  } catch (error) {
    console.error('Failed to fetch tasks', error);
  }
};

onMounted(() => {
  fetchAllTasks(); // Fetch tasks when the component mounts
});

</script>
<template>
    <v-card elevation="10" class="withbg">
        <v-card-item>
            <div class="d-sm-flex align-center justify-space-between pt-sm-2">
                <v-card-title class="text-h5">Yearly Breakup</v-card-title>
            </div>
            <v-row>
                <v-col cols="7" sm="7">
                    <div class="mt-6">
                        <h3 class="text-h3">$36,358</h3>
                        <div class="mt-1">
                            <v-avatar class="bg-lightsuccess text-success" size="25">
                                <ArrowUpLeftIcon size="20" />
                            </v-avatar>
                            <span class="text-subtitle-1 ml-2 font-weight-bold">+9%</span>
                            <span class="text-subtitle-1 text-muted ml-2">last year</span>
                        </div>
                        <div class="d-flex align-center mt-sm-10 mt-8">
                            <h6 class="text-subtitle-1 text-muted">
                                <v-icon icon="mdi mdi-checkbox-blank-circle" class="mr-1" size="10" color="primary"></v-icon> 2022
                            </h6>
                            <h6 class="text-subtitle-1 text-muted pl-5">
                                <v-icon icon="mdi mdi-checkbox-blank-circle" class="mr-1" size="10" color="lightprimary"></v-icon> 2023
                            </h6>
                        </div>
                    </div>
                </v-col>
                <v-col cols="5" sm="5" class="pl-lg-0">
                    <div class="d-flex align-center flex-shrink-0">
                        <apexchart class="pt-6" type="donut" height="145" :options="chartOptions" :series="Chart"> </apexchart>
                    </div>
                </v-col>
            </v-row>
        </v-card-item>
    </v-card>
</template>
