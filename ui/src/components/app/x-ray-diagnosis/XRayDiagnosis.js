import React, { useEffect } from 'react';
import { StyledAppFrame } from '../../platform/widgets/AppFrame';
import GroupFrame from '../../platform/widgets/GroupFrame';
import axios from 'axios';

export default class XRayDiagnosis extends React.Component {
  render() {
    return (
      <StyledAppFrame>
        <GroupFrame
          groupPane={<div>group pane</div>}
          listPane={<div>list pane</div>}
          detailPane={<div>detail pane</div>}
        />
      </StyledAppFrame>
    );
  }
}

function ThingPane() {
  useEffect(() => {
    axios.get('api/v1/session/login');
  });
}
