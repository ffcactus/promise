import style from 'styled-components';

const Input = style.input.attrs(({ size }) => ({
  // we can define static props
  type: 'password',

  // or we can define dynamic ones
  margin: size || '1em',
  padding: size || '1em'
}))`
  color: palevioletred;
  font-size: 1em;
  border: 2px solid palevioletred;
  border-radius: 3px;

  /* here we use the dynamically computed props */
  margin: ${props => props.margin};
  padding: ${props => props.padding};
`;

export default Input;
