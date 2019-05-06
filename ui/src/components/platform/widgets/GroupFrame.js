import React from 'react';
import SplitPane from 'react-split-pane';
import PropTypes from 'prop-types';
import styled from 'styled-components';

const StyledOutterSplitPane = styled(SplitPane).attrs(
  ({ groupDefaultSize, groupMinSize }) => ({
    split: 'vertical',
    minSize: groupMinSize || 50,
    defaultSize: groupDefaultSize || '14.6%'
    // resizerClassName: "Resizer"
  })
)`
  background-color: white;
  position: absolute;
  top: 0px;
  right: 0px;
  bottom: 0px;
  left: 0px;

  .Resizer {
    background-color: ${p => p.theme.backgroundColor};
    opacity: 0.4;
    z-index: 1;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    -moz-background-clip: padding;
    -webkit-background-clip: padding;
    background-clip: padding-box;
  }

  .Resizer:hover {
    -webkit-transition: all 2s ease;
    transition: all 1s ease;
  }

  .Resizer.horizontal {
    height: 11px;
    margin: -5px 0;
    border-top: 5px solid rgba(255, 255, 255, 0);
    border-bottom: 5px solid rgba(255, 255, 255, 0);
    cursor: row-resize;
    width: 100%;
  }

  .Resizer.horizontal:hover {
    border-top: 5px solid rgba(0, 0, 0, 0.5);
    border-bottom: 5px solid rgba(0, 0, 0, 0.5);
  }

  .Resizer.vertical {
    width: 11px;
    margin: 0 -5px;
    border-left: 5px solid rgba(255, 255, 255, 0);
    border-right: 5px solid rgba(255, 255, 255, 0);
    cursor: col-resize;
  }

  .Resizer.vertical:hover {
    border-left: 5px solid rgba(0, 0, 0, 0.5);
    border-right: 5px solid rgba(0, 0, 0, 0.5);
  }

  .Resizer.disabled {
    cursor: not-allowed;
  }

  .Resizer.disabled:hover {
    border-color: transparent;
  }
`;

const StyledInnerSplitPane = styled(SplitPane).attrs(
  ({ listMinSize, listDefaultSize }) => ({
    split: 'vertical',
    minSize: listMinSize || 50,
    defaultSize: listDefaultSize || '27.6%'
    // resizerClassName: "Resizer"
  })
)``;

/**
 * GroupFrame represents the 3 elements in body, the groupPane, listPane, and detailPane in vertial style.
 * You can resize each of the pane. It should always take the whole area of the parent.
 */
class GroupFrame extends React.Component {
  render() {
    return (
      <StyledOutterSplitPane {...this.props}>
        {this.props.groupPane}
        <StyledInnerSplitPane {...this.props}>
          {this.props.listPane}
          {this.props.detailPane}
        </StyledInnerSplitPane>
      </StyledOutterSplitPane>
    );
  }
}

GroupFrame.propTypes = {
  styles: PropTypes.object,
  groupPane: PropTypes.object,
  listPane: PropTypes.object,
  detailPane: PropTypes.object
};

export default GroupFrame;
