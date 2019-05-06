const defaultTheme = {
  backgroundColor: 'black',
  color: 'green',
  boxRadiusPx: 6,
  outMostGapPx: 12,
  button: {
    default: {
      color: 'rgba(0, 0, 0, 0.87)',
      backgroundColor: '#2196f3'
    },
    primary: {
      color: 'white',
      backgroundColor: '#2196f3'
    },
    secondary: {
      color: 'white',
      backgroundColor: 'rgb(225, 0, 80)'
    }
  },
  dialog: {
    maxWidthPer: 80,
    maxHeightPer: 80,
    backgroundColor: 'whitesmoke',
    title: {
      heightPx: 40,
      backgroundColor: 'black',
      color: 'silver',
      fontSizePx: 21
    },
    control: {
      backgroundColor: '#c0c0c06e',
      heightPx: 40
    },
    message: {
      maxHeightPx: 80
    }
  }
};

export default defaultTheme;
