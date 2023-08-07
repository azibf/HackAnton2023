import {Button} from "@mui/material";


export default function Dashboard() {
    const handleClick = () => {
        alert('click')
    }
    return (
       <div className='content'>
           <Button onClick={handleClick} variant="contained">Button</Button>
       </div>
    )
}
