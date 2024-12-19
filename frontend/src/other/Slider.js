import {Carousel, Image} from 'react-bootstrap';
import "./Slider.css"

function Slider() {
  return (
    <Carousel className='w-75'>
      <Carousel.Item>
        <Image className="d-block w-100 slider-image"  src = "https://cs14.pikabu.ru/post_img/2024/01/02/11/1704222580111699179.jpg"/>
        <Carousel.Caption>
          <h3>Современное оборудование</h3>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
      <Image className="d-block w-100 slider-image"  src = "https://previews.123rf.com/images/erierika/erierika1208/erierika120800024/14749933-%D0%B7%D0%B0%D0%B1%D0%B0%D0%B2%D0%BD%D1%8B%D0%B9-%D0%BA%D0%BB%D0%BE%D1%83%D0%BD-%D0%B2%D1%80%D0%B0%D1%87-%D1%81%D0%BE-%D1%81%D1%82%D0%B5%D1%82%D0%BE%D1%81%D0%BA%D0%BE%D0%BF%D0%BE%D0%BC-%D0%BD%D0%BE%D1%81%D0%B8%D1%82%D1%8C-%D0%BA%D1%80%D0%B0%D1%81%D0%BD%D1%8B%D0%B9-%D0%BD%D0%BE%D1%81-%D0%B8-%D0%BA%D1%80%D0%B0%D1%81%D0%BE%D1%87%D0%BD%D1%8B%D0%B9.jpg"/>
        <Carousel.Caption>
          <h3>Надёжный персонал</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <Image className="d-block w-100 slider-image"  src = "https://sun9-34.userapi.com/impf/f9lThNAJaF7v-wAE3zczEv8xu0Jih8xGrlAUfw/RhzgakXk0pk.jpg?size=604x362&quality=96&sign=92e6f9256ce53d305084145f32d1fbb2&type=album"/>
        <Carousel.Caption>
          <h3>Низкие цены</h3>
          <p>
            Praesent commodo cursus magna, vel scelerisque nisl consectetur.
          </p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
}

export default Slider;