import {Radar} from "react-chartjs-2";
import React from 'react'

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  LineElement,
  PointElement,
  RadialLinearScale,
  ArcElement,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  LineElement,
  PointElement,
  RadialLinearScale,
  ArcElement,
  Tooltip,
  Legend
);


export default function LinearGraph({data}) {

    const numericData = data.data.map(val =>{
        const num = parseFloat(val);
        return isNaN(num) ? 0 : num;
    })

    const chartData = {
        labels: data.labels,
        datasets:[
            {
                label: "Health Metrics",
                data: numericData,
                backgroundColor: ["rgba(59, 130, 26, 0.5)", "rgba(34, 197, 94, 0.5)", "rgba(16, 185, 129, 0.5)", "rgba(6, 182, 212, 0.5)"],
                borderColor: ["rgba(5, 130, 26, 1)", "rgba(22, 163, 74, 1)", "rgba(5, 150, 105, 1)", "rgba(8, 145, 178, 1)"],
                borderWidth: 1,
            }
        ]
    }

    const options = {
    responsive: true,
    maintainAspectRatio: true,
  };
  return (
    <div>
        {/* <Bar data={chartData} options={options} /> */}
        <Radar data={chartData} options={options} />
    </div>
  )
}
