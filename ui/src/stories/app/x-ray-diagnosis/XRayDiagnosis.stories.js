import { storiesOf } from '@storybook/react';
import XRayDiagnosis from '../../../components/app/x-ray-diagnosis/XRayDiagnosis';

storiesOf('App/XRayDiagnosis', module).add('Default', () => {
  return <XRayDiagnosis />;
});
