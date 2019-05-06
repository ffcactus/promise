import React from 'react';
import { storiesOf } from '@storybook/react';
import GroupFrame from '../../../components/platform/widgets/GroupFrame';

storiesOf('Platform/widgets/GroupFrame', module)
  .add('default', () => {
    const groupPane = <div>Group Pane</div>;
    const listPane = <div>List Pane</div>;
    const detailPane = <div>Detail Pane</div>;
    return (
      <GroupFrame
        groupPane={groupPane}
        listPane={listPane}
        detailPane={detailPane}
      />
    );
  })
  .add('change-default-size', () => {
    const groupPane = (
      <div>
        Group Pane default size 33%, so 66% left for list pane and detail pane.
      </div>
    );
    const listPane = (
      <div>List Pane default size 50%, so 50% left for detail pane.</div>
    );
    const detailPane = (
      <div>Detail Pane should be the same size as list pane.</div>
    );
    return (
      <GroupFrame
        groupPane={groupPane}
        listPane={listPane}
        detailPane={detailPane}
        groupDefaultSize="33%"
        listDefaultSize="50%"
      />
    );
  })
  .add('generate scrollbar', () => {
    const groupPane = (
      <div style={{ overflow: 'auto', height: '100%' }}>
        <span>
          Group pane has a div which has width of 200px.
          <br />
          Group pane has style of "overflow: auto".
          <br />
          So when you resize this area you should see the scrollbar.
        </span>
        <hr />
        <div
          style={{
            width: '200px',
            backgroundColor: 'lightgreen',
            height: '200px'
          }}
        >
          Div with 200px
        </div>
      </div>
    );
    const listPane = <div>List Pane</div>;
    const detailPane = <div>Detail Pane</div>;
    return (
      <GroupFrame
        groupPane={groupPane}
        listPane={listPane}
        detailPane={detailPane}
      />
    );
  });
