const presets = [
  [
    "@babel/env",
    {
      targets: {
        edge: "17",
        firefox: "60",
        chrome: "67",
        safari: "11.1",
      },
      useBuiltIns: "usage",
    },
    "@babel/preset-env", 
    "@babel/preset-react"
  ],
];

const plugins = [
  [
    "@babel/plugin-proposal-class-properties",
    {
      "loose": true
    }
    "@babel/plugin-transform-arrow-functions", 
    "@babel/plugin-proposal-    class-properties"
  ]
]

module.exports = { presets, plugins };