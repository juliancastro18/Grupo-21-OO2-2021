const submitButton = document.querySelector('#submit-btn');
const oldPassword = document.querySelector('#oldpassword')
const password = document.querySelector('#password')


function preSubmit(){
    console.log('aaaaaaa')
    if(password.value === ''){
        password.value = oldPassword.value;
    }
    return true;

}