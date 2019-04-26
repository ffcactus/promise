import styled from 'styled-components';

const Input = styled.input`
  display: block;
  height: 36px;
  width: 18em;
  box-sizing: border-box;
  border: 1px solid;
  border-color: silver;
  border-radius: ${p => p.theme.boxRadiusPx}px;
  margin-bottom: ${p => p.theme.boxRadiusPx}px;
  padding: 0 15px;
  font-size: 17px;
  line-height: 1.29;
  font-weight: normal;
  font-family: -apple-system, BlinkMacSystemFont, system-ui;
`;

export default Input;
