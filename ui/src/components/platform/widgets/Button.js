import styled from 'styled-components';

const Button = styled.button`
  min-width: 64px;
  background-color: ${p => {
    if (p.primary) {
      return p.theme.button.primary.backgroundColor;
    }
    if (p.secondary) {
      return p.theme.button.secondary.backgroundColor;
    }
    return 'transparent';
  }};
  border-radius: ${p => p.theme.boxRadiusPx}px;
  border: 0px;
  color: ${p => {
    if (p.primary) {
      return p.theme.button.primary.color;
    }
    if (p.secondary) {
      return p.theme.button.secondary.color;
    }
    return p.theme.button.default.color;
  }};
  margin: 8px;
  padding: 0.25em 1em;
  box-shadow: 0px 1px 5px 0px rgba(0, 0, 0, 0.2),
    0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 3px 1px -2px rgba(0, 0, 0, 0.12);
  cursor: pointer;
  outline: none;
  vertical-align: middle;
  justify-content: center;
  align-items: center;
  user-select: none;
  cursor: pointer;
  text-decoration: none; /* To remove the underlink if the a link is rendered as a button */
`;

export default Button;
