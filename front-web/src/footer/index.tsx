import {ReactComponent as InstagramLogo} from "./instagram.svg"
import "./style.css"

function Footer() {
    return(
        <footer className = "main-footer">    
            App desenvolvido durante a 2ª ed. do evento Semana DevSuperior
            <div className = "footer-icons">
               <a href="https://www.instagram.com/kavalciukijoao/" target = "_new">
                    <InstagramLogo/>
               </a>
            </div>
        </footer>
    );
}

export default Footer;