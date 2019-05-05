import React from 'react';
import { connect } from 'react-redux';
import { RegisterState } from './ConstValue';
import RegisterSuccessDialog from './RegisterSuccessDialog';
import RegisterDialog from './RegisterDialog';

class RegisterContainer extends React.Component {
  render() {
    switch (this.props.register.state) {
      case RegisterState.REGISTERING:
        return <RegisterDialog {...this.props} />;
      case RegisterState.SUCCESS:
        return <RegisterSuccessDialog />;
      default:
        console.info('Reach unexpected code.');
        return <RegisterDialog />;
    }
  }
}

function mapStateToProps(state) {
  const { register } = state;
  return { register };
}

export default connect(mapStateToProps)(RegisterContainer);
