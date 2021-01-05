# styled-component

## When dealing with nested HTML elements, should I use multiple styled components or the CSS selector?

My current understanding is it depends on if those nested children components can be used independently in other places.
Take this form for example:

```javascript
<div id="dialog">
  <div id="header">
    <span>Login</span>
  </div>
  <div id="content">
    <form>
      <section>
        <label for="username" />
        <input id="username" />
        <label for="password" />
        <input id="username" type="password" />
      </section>
      <section>
        <input type="submit" />
      </section>
    </form>
  </div>
</div>
```

You probably need to style all of the elements. I think I will:

1. Define these components as common ones and save them elsewhere:

```javascript
const StyledDialog = styled.div\``;         // widgets/StyledDialog.js
const StyledDialogHeader = styled.div\``;   // widgets/StyledDialog.js
const StyledDialogContent = styled.div\``;  // widgets/StyledDialog.js
const StyledInput = styled.input\``;        // widgets/StyledInput.js
```

2. Use the CSS selector to override the style when I need my fancy dialog.

```javascript
const FancyDialog = styled(StyledDialog)`
  /* Use the CSS selector to style the span section and so on, because they are near to the code that uses this FancyDialog. */
`;
```
