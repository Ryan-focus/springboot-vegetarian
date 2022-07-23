import { createRouter, createWebHashHistory, RouterView } from "vue-router";

import NProgress from "nprogress/nprogress.js";

// Main layout variations
//設定Layout模板
import LayoutSimple from "@/layouts/variations/Simple.vue";
import LayoutLanding from "@/layouts/variations/Landing.vue";
import LayoutBackend from "@/layouts/variations/Backend.vue";
import LayoutBackendBoxed from "@/layouts/variations/BackendBoxed.vue";
import LayoutBackendMegaMenu from "@/layouts/variations/BackendMegaMenu.vue";

//設定Business LayOuting 模板
import BLayoutBackend from "@/BusinessLayouts/variations/Backend.vue";
// import BLayoutBackendBoxed from "@/BusinessLayouts/variations/BackendBoxed.vue";
// import BLayoutBackendMegaMenu from "@/BusinessLayouts/variations/BackendMegaMenu.vue";

//引入跳轉空頁面
const jump = () => import("@/views/blank/jump.vue");
// Frontend: Index
const Index = () => import("@/views/frontend/Index.vue");
const Login = () => import("@/views/frontend/Login.vue");
const UserRegister = () => import("@/views/frontend/Register.vue");
const MemberArea = () => import("@/views/frontend/MemberArea.vue");
const Verification = () => import("@/views/frontend/Verification.vue");

//購物車
const ShoppingCart = () => import("@/views/frontend/ShoppingCart.vue");
const ShoppingOrder = () => import("@/views/frontend/ShoppingOrder.vue");
const ShoppingCheckOut = () => import("@/views/frontend/CheckOut.vue");
const ShoppingCheckOutSuccess = () =>
  import("@/views/frontend/CheckOutSuccess.vue");
const ShoppingCartItem = () => import("@/views/frontend/ShoppingCartItem.vue");

const Post = () => import("@/views/frontend/Post.vue");
const PostContent = () => import("@/views/frontend/PostContent.vue");
const PostFavorite = () => import("@/views/frontend/PostFavorite.vue");
const PostCreateFront = () => import("@/views/frontend/CreatePost.vue");
const PostEditFront = () => import("@/views/frontend/EditPost.vue");
const SearchRestaurant = () => import("@/views/frontend/SearchRestaurant.vue");
const RestaurantDetails = () => import("@/views/frontend/Restaurant.vue");
const ForumIndex = () => import("@/views/frontend/ForumIndex.vue");
const ForumPage = () => import("@/views/frontend/ForumPage.vue");
//Customer 商家 預訂 後台
const BusinessRegister = () => import("@/views/frontend/BusinessRegister.vue");
const BusinessBackend = () => import("@/views/frontend/BusinessBackend.vue");
const BusinessProfileView = () =>
  import("@/views/frontend/businessBackend/BusinessProfileView.vue");
const BusinessCalendar = () =>
  import("@/views/frontend/businessBackend/BusinessCalendar.vue");
const ReserveOrder = () => import("@/views/frontend/ReserveOrder.vue");
const ReserveDetail = () => import("@/views/frontend/ReserveDetail.vue");

// Backend Boxed: Dashboard
const BackendBoxedDashboard = () =>
  import("@/views/backend-boxed/DashboardView.vue");
const BackendBoxedSimple1 = () =>
  import("@/views/backend-boxed/Simple1View.vue");
const BackendBoxedSimple2 = () =>
  import("@/views/backend-boxed/Simple2View.vue");
const BackendBoxedImage1 = () => import("@/views/backend-boxed/Image1View.vue");
const BackendBoxedImage2 = () => import("@/views/backend-boxed/Image2View.vue");
const BackendBoxedSearch = () => import("@/views/backend-boxed/SearchView.vue");

// Backend: Dashboard
const BackendDashboard = () => import("@/views/backend/DashboardView.vue");

//愛蔬網後台: users
const VeganUsersDashboard = () =>
  import("@/views/vegan/users/DashboardView.vue");
const VeganUsersInfo = () => import("@/views/vegan/users/UsersInfo.vue");
//愛蔬網後台: restaurant
const VeganRestaurantDashboard = () =>
  import("@/views/vegan/restaurant/DashboardView.vue");
const VeganRestaurantInfo = () =>
  import("@/views/vegan/restaurant/RestaurantInfo.vue");
const VeganRestaurantInsertRestaurant = () =>
  import("@/views/vegan/restaurant/InsertRestaurant.vue");
//愛蔬網後台: forums
const VeganForumsDashboard = () =>
  import("@/views/vegan/forums/DashboardView.vue");
const VeganForumsForumsInfo = () =>
  import("@/views/vegan/forums/ForumsInfo.vue");
const VeganForumsInsertForum = () =>
  import("@/views/vegan/forums/InsertForum.vue");

//愛蔬網後台: posts
const VeganPostsDashboard = () =>
  import("@/views/vegan/posts/DashboardView.vue");
const VeganPostsPostsInfo = () => import("@/views/vegan/posts/PostsInfo.vue");
const VeganPostsCreatePost = () => import("@/views/vegan/posts/CreatePost.vue");
const VeganPostsEditPost = () => import("@/views/vegan/posts/EditPost.vue");
//愛蔬網後台: cart
const VeganCartDashboard = () => import("@/views/vegan/cart/DashboardView.vue");
const VeganCartProductInfo = () => import("@/views/vegan/cart/ProductInfo.vue");
const VeganCartOrderInfo = () => import("@/views/vegan/cart/OrderInfo.vue");
const VeganCartInsertProduct = () =>
  import("@/views/vegan/cart/InsertProduct.vue");
const VeganImageTest = () => import("@/views/vegan/cart/ImageTest.vue");
const VeganImagUpload = () => import("@/views/vegan/cart/imageUpload.vue");

//愛蔬網後台: reserve
const VeganReserveDashboard = () =>
  import("@/views/vegan/reserve/DashboardView.vue");
const VeganReserveInfo = () => import("@/views/vegan/reserve/ReserveInfo.vue");

// Backend: Blocks
const BackendBlocksStyles = () =>
  import("@/views/backend/blocks/StylesView.vue");
const BackendBlocksOptions = () =>
  import("@/views/backend/blocks/OptionsView.vue");
const BackendBlocksForms = () => import("@/views/backend/blocks/FormsView.vue");
const BackendBlocksThemed = () =>
  import("@/views/backend/blocks/ThemedView.vue");
const BackendBlocksApi = () => import("@/views/backend/blocks/ApiView.vue");

// Backend: Elements
const BackendElementsGrid = () =>
  import("@/views/backend/elements/GridView.vue");
const BackendElementsTypography = () =>
  import("@/views/backend/elements/TypographyView.vue");
const BackendElementsIcons = () =>
  import("@/views/backend/elements/IconsView.vue");
const BackendElementsButtons = () =>
  import("@/views/backend/elements/ButtonsView.vue");
const BackendElementsButtonGroups = () =>
  import("@/views/backend/elements/ButtonGroupsView.vue");
const BackendElementsDropdowns = () =>
  import("@/views/backend/elements/DropdownsView.vue");
const BackendElementsTabs = () =>
  import("@/views/backend/elements/TabsView.vue");
const BackendElementsNavigation = () =>
  import("@/views/backend/elements/NavigationView.vue");
const BackendElementsNavigationHorizontal = () =>
  import("@/views/backend/elements/NavigationHorizontalView.vue");
const BackendElementsMegaMenu = () =>
  import("@/views/backend/elements/MegaMenuView.vue");
const BackendElementsProgress = () =>
  import("@/views/backend/elements/ProgressView.vue");
const BackendElementsAlerts = () =>
  import("@/views/backend/elements/AlertsView.vue");
const BackendElementsTooltips = () =>
  import("@/views/backend/elements/TooltipsView.vue");
const BackendElementsPopovers = () =>
  import("@/views/backend/elements/PopoversView.vue");
const BackendElementsModals = () =>
  import("@/views/backend/elements/ModalsView.vue");
const BackendElementsImagesOverlay = () =>
  import("@/views/backend/elements/ImagesOverlayView.vue");
const BackendElementsTimeline = () =>
  import("@/views/backend/elements/TimelineView.vue");
const BackendElementsRibbons = () =>
  import("@/views/backend/elements/RibbonsView.vue");
const BackendElementsAnimations = () =>
  import("@/views/backend/elements/AnimationsView.vue");
const BackendElementsColorThemes = () =>
  import("@/views/backend/elements/ColorThemesView.vue");

// Backend: Tables
const BackendTablesStyles = () =>
  import("@/views/backend/tables/StylesView.vue");
const BackendTablesResponsive = () =>
  import("@/views/backend/tables/ResponsiveView.vue");
const BackendTablesHelpers = () =>
  import("@/views/backend/tables/HelpersView.vue");

// Backend: Forms
const BackendFormsElements = () =>
  import("@/views/backend/forms/ElementsView.vue");
const BackendFormsLayouts = () =>
  import("@/views/backend/forms/LayoutsView.vue");
const BackendFormsInputGroups = () =>
  import("@/views/backend/forms/InputGroupsView.vue");
const BackendFormsPlugins = () =>
  import("@/views/backend/forms/PluginsView.vue");
const BackendFormsEditors = () =>
  import("@/views/backend/forms/EditorsView.vue");
const BackendFormsValidation = () =>
  import("@/views/backend/forms/ValidationView.vue");

// Backend: Plugins
const BackendPluginsImageCropper = () =>
  import("@/views/backend/plugins/ImageCropperView.vue");
const BackendPluginsCharts = () =>
  import("@/views/backend/plugins/ChartsView.vue");
const BackendPluginsCalendar = () =>
  import("@/views/backend/plugins/CalendarView.vue");
const BackendPluginsCarousel = () =>
  import("@/views/backend/plugins/CarouselView.vue");
const BackendPluginsSyntaxHighlighting = () =>
  import("@/views/backend/plugins/SyntaxHighlightingView.vue");
const BackendPluginsRating = () =>
  import("@/views/backend/plugins/RatingView.vue");
const BackendPluginsDialogs = () =>
  import("@/views/backend/plugins/DialogsView.vue");
const BackendPluginsNotifications = () =>
  import("@/views/backend/plugins/NotificationsView.vue");
const BackendPluginsGallery = () =>
  import("@/views/backend/plugins/GalleryView.vue");

// Backend: Layout
const BackendLayoutPageDefault = () =>
  import("@/views/backend/layout/page/DefaultView.vue");
const BackendLayoutPageFlipped = () =>
  import("@/views/backend/layout/page/FlippedView.vue");
const BackendLayoutDarkModeOn = () =>
  import("@/views/backend/layout/dark-mode/OnView.vue");
const BackendLayoutDarkModeOff = () =>
  import("@/views/backend/layout/dark-mode/OffView.vue");
const BackendLayoutDarkModeSystem = () =>
  import("@/views/backend/layout/dark-mode/SystemView.vue");
const BackendLayoutMainContentFullWidth = () =>
  import("@/views/backend/layout/main-content/FullWidthView.vue");
const BackendLayoutMainContentNarrow = () =>
  import("@/views/backend/layout/main-content/NarrowView.vue");
const BackendLayoutMainContentBoxed = () =>
  import("@/views/backend/layout/main-content/BoxedView.vue");
const BackendLayoutHeaderFixedLight = () =>
  import("@/views/backend/layout/header/FixedLightView.vue");
const BackendLayoutHeaderFixedDark = () =>
  import("@/views/backend/layout/header/FixedDarkView.vue");
const BackendLayoutHeaderStaticLight = () =>
  import("@/views/backend/layout/header/StaticLightView.vue");
const BackendLayoutHeaderStaticDark = () =>
  import("@/views/backend/layout/header/StaticDarkView.vue");
const BackendLayoutSidebarMini = () =>
  import("@/views/backend/layout/sidebar/MiniView.vue");
const BackendLayoutSidebarLight = () =>
  import("@/views/backend/layout/sidebar/LightView.vue");
const BackendLayoutSidebarDark = () =>
  import("@/views/backend/layout/sidebar/DarkView.vue");
const BackendLayoutSidebarHidden = () =>
  import("@/views/backend/layout/sidebar/HiddenView.vue");
const BackendLayoutSideOverlayVisible = () =>
  import("@/views/backend/layout/side-overlay/VisibleView.vue");
const BackendLayoutSideOverlayHoverMode = () =>
  import("@/views/backend/layout/side-overlay/HoverModeView.vue");
const BackendLayoutSideOverlayNoPageOverlay = () =>
  import("@/views/backend/layout/side-overlay/NoPageOverlayView.vue");
const BackendLayoutLoaders = () =>
  import("@/views/backend/layout/LoadersView.vue");
const BackendLayoutApi = () => import("@/views/backend/layout/ApiView.vue");

// Backend: Generic Pages
const BackendPagesGenericBlank = () =>
  import("@/views/backend/pages/generic/BlankView.vue");
const BackendPagesGenericBlankBlock = () =>
  import("@/views/backend/pages/generic/BlankBlockView.vue");
const BackendPagesGenericSearch = () =>
  import("@/views/backend/pages/generic/SearchView.vue");
const BackendPagesGenericProfile = () =>
  import("@/views/backend/pages/generic/ProfileView.vue");
const BackendPagesGenericInvoice = () =>
  import("@/views/backend/pages/generic/InvoiceView.vue");
const BackendPagesGenericPricingPlans = () =>
  import("@/views/backend/pages/generic/PricingPlansView.vue");
const BackendPagesGenericFaq = () =>
  import("@/views/backend/pages/generic/FaqView.vue");
const BackendPagesGenericInbox = () =>
  import("@/views/backend/pages/generic/InboxView.vue");

// Backend: Pages
const BackendPagesAuth = () => import("@/views/backend/pages/AuthView.vue");
const BackendPagesErrors = () => import("@/views/backend/pages/ErrorsView.vue");

// Specials
const SpecialsMaintenance = () =>
  import("@/views/specials/MaintenanceView.vue");
const SpecialsStatus = () => import("@/views/specials/StatusView.vue");
const SpecialsComingSoon = () => import("@/views/specials/ComingSoonView.vue");

// Auth
const AuthSignIn = () => import("@/views/auth/SignInView.vue");
const AuthSignIn2 = () => import("@/views/auth/SignIn2View.vue");
const AuthSignIn3 = () => import("@/views/auth/SignIn3View.vue");
const AuthSignUp = () => import("@/views/auth/SignUpView.vue");
const AuthSignUp2 = () => import("@/views/auth/SignUp2View.vue");
const AuthSignUp3 = () => import("@/views/auth/SignUp3View.vue");
const AuthLock = () => import("@/views/auth/LockView.vue");
const AuthLock2 = () => import("@/views/auth/Lock2View.vue");
const AuthLock3 = () => import("@/views/auth/Lock3View.vue");
const AuthReminder = () => import("@/views/auth/ReminderView.vue");
const AuthReminder2 = () => import("@/views/auth/Reminder2View.vue");
const AuthReminder3 = () => import("@/views/auth/Reminder3View.vue");

// Errors
const Error400 = () => import("@/views/errors/400View.vue");
const Error401 = () => import("@/views/errors/401View.vue");
const Error403 = () => import("@/views/errors/403View.vue");
const Error404 = () => import("@/views/errors/404View.vue");
const Error500 = () => import("@/views/errors/500View.vue");
const Error503 = () => import("@/views/errors/503View.vue");

//愛蔬網 Backend 頁面

// Set all routes
const routes = [
  /*
  |
  |--------------------------------------------------------------------------
  | Landing Routes
  |--------------------------------------------------------------------------
  |
  */
  {
    path: "/",
    component: LayoutLanding,
    children: [
      {
        path: "",
        name: "index",
        component: Index,
      },
      {
        path: "/signin",
        name: "login",
        component: Login,
      },
      {
        path: "/user/register",
        name: "userRegister",
        component: UserRegister,
      },
      {
        path: "/user/verification",
        name: "Verification",
        component: Verification,
      },
      {
        path: "/memberArea",
        name: "MemberArea",
        component: MemberArea,
      },
      {
        path: "/support/store",
        name: "BusinessRegister",
        component: BusinessRegister,
      },
      // 購物車
      {
        path: "/shopping",
        name: "shoppingCart",
        component: ShoppingCart,
      },
      {
        path: "/shopping/order",
        name: "shoppingOrder",
        component: ShoppingOrder,
      },
      {
        path: "/shopping/checkout",
        name: "shoppingCheckOut",
        component: ShoppingCheckOut,
      },
      {
        path: "/shopping/checkoutSuccess",
        name: "shoppingCheckOutSuccess",
        component: ShoppingCheckOutSuccess,
      },
      {
        path: "/shopping/cartItem",
        name: "shoppingCartItem",
        component: ShoppingCartItem,
      },
      //食記
      {
        path: "/post",
        name: "postIndex",
        component: Post,
      },
      {
        path: "/postContent/:postId?",
        name: "postPage",
        component: PostContent,
      },
      {
        path: "/postFavorite",
        name: "postFavorite",
        component: PostFavorite,
      },
      {
        path: "/postCreate",
        name: "postCreateFront",
        component: PostCreateFront,
      },
      {
        path: "/postEdit/:postId?",
        name: "postEditFront",
        component: PostEditFront,
      },
      //餐廳
      {
        path: "/searchRestaurant/:searchAddress?/:searchName?",
        name: "restaurantIndex",
        component: SearchRestaurant,
      },
      // {
      //   path: "/searchRestaurant/:restaurantCategory?/:restaurantType?/:searchName?/:searchAddress?",
      //   name: "restaurantIndex",
      //   component: SearchRestaurant,
      // },
      {
        path: "/searchRestaurant/details/:restaurantNumber?",
        name: "restaurant-details",
        component: RestaurantDetails,
      },
      {
        path: "/searchRestaurant/reserve/:restaurantId?/:businessId?",
        name: "restaurant-reserve",
        component: ReserveOrder,
      },
      {
        path: "/business/backend/dashboard",
        name: "business-backend-dashboard",
        component: BusinessBackend,
      },
      {
        path: "/business/backend/profile",
        name: "business-backend-profile",
        component: BusinessProfileView,
      },
      {
        path: "/forumIndex",
        name: "Forum-index",
        component: ForumIndex,
      },
      {
        path: "/forumPage",
        name: "Forum-page",
        component: ForumPage,
      },
      {
        path: "/jump",
        name: "blank",
        component: jump,
      },
      {
        path: "/searchRestaurant/check",
        name: "restaurant-reserve-check",
        component: ReserveDetail,
      },
    ],
  },

  /*
  |
  |--------------------------------------------------------------------------
  | Backend Boxed Routes
  |--------------------------------------------------------------------------
  |
  */
  {
    path: "/backend-boxed",
    component: LayoutBackendBoxed,
    children: [
      {
        path: "",
        name: "backend-boxed-dashboard",
        component: BackendBoxedDashboard,
      },
      {
        path: "simple1",
        name: "backend-boxed-simple1",
        component: BackendBoxedSimple1,
      },
      {
        path: "simple2",
        name: "backend-boxed-simple2",
        component: BackendBoxedSimple2,
      },
      {
        path: "image1",
        name: "backend-boxed-image1",
        component: BackendBoxedImage1,
      },
      {
        path: "image2",
        name: "backend-boxed-image2",
        component: BackendBoxedImage2,
      },
      {
        path: "search",
        name: "backend-boxed-search",
        component: BackendBoxedSearch,
      },
    ],
  },

  /*
  |--------------------------------------------------------------------------
  | Backend Mega Menu Route
  |--------------------------------------------------------------------------
  */

  {
    path: "/backend/elements",
    component: LayoutBackendMegaMenu,
    children: [
      {
        path: "mega-menu",
        name: "backend-elements-mega-menu",
        component: BackendElementsMegaMenu,
      },
    ],
  },

  /*
  |
  |--------------------------------------------------------------------------
  | Backend Routes
  |--------------------------------------------------------------------------
  |
  */
  {
    path: "/backend",
    redirect: "/backend/dashboard",
    component: LayoutBackend,
    children: [
      {
        path: "dashboard",
        name: "backend-dashboard",
        component: BackendDashboard,
      },

      /*
      |
      |--------------------------------------------------------------------------
      | 愛蔬網後台 Backend Routes
      |--------------------------------------------------------------------------
      |
      */
      {
        path: "users",
        redirect: "/users/dashboard",
        component: RouterView,
        children: [
          {
            path: "dashboard",
            name: "backend-users-dashboard",
            component: VeganUsersDashboard,
          },
          {
            path: "userInfo",
            name: "backend-users-user-info",
            component: VeganUsersInfo,
          },
        ],
      },
      {
        path: "restaurants",
        redirect: "/restaurants/dashboard",
        component: RouterView,
        children: [
          {
            path: "dashboard",
            name: "backend-restaurants-dashboard",
            component: VeganRestaurantDashboard,
          },
          {
            path: "restaurantinfo",
            name: "backend-restaurants-restaurant-info",
            component: VeganRestaurantInfo,
          },
          {
            path: "insertRestaurant",
            name: "backend-restaurants-insert-restaurant",
            component: VeganRestaurantInsertRestaurant,
          },
        ],
      },
      {
        path: "posts",
        redirect: "/posts/dashboard",
        component: RouterView,
        children: [
          {
            path: "dashboard",
            name: "backend-posts-dashboard",
            component: VeganPostsDashboard,
          },
          {
            path: "postinfo",
            name: "backend-posts-posts-info",
            component: VeganPostsPostsInfo,
          },
          {
            path: "create",
            name: "backend-posts-create",
            component: VeganPostsCreatePost,
          },
          {
            path: "edit/:postId?",
            name: "backend-posts-edit",
            component: VeganPostsEditPost,
          },
        ],
      },
      {
        path: "forums",
        redirect: "/forums/dashboard",
        component: RouterView,
        children: [
          {
            path: "dashboard",
            name: "backend-forums-dashboard",
            component: VeganForumsDashboard,
          },
          {
            path: "forumsinfo",
            name: "backend-forums-forums-info",
            component: VeganForumsForumsInfo,
          },
          {
            path: "insertForum",
            name: "backend-forums-insert-forum",
            component: VeganForumsInsertForum,
          },
        ],
      },
      {
        path: "cart",
        redirect: "/cart/dashboard",
        component: RouterView,
        children: [
          {
            path: "dashboard",
            name: "backend-cart-dashboard",
            component: VeganCartDashboard,
          },
          {
            path: "productInfo",
            name: "backend-cart-product-info",
            component: VeganCartProductInfo,
          },
          {
            path: "orderInfo",
            name: "backend-cart-order-info",
            component: VeganCartOrderInfo,
          },
          {
            path: "insertProduct",
            name: "backend-cart-insert-product",
            component: VeganCartInsertProduct,
          },
          {
            path: "imageTest",
            name: "backend-cart-image-test",
            component: VeganImageTest,
          },
          {
            path: "imageUpload",
            name: "backend-cart-image-upload",
            component: VeganImagUpload,
          },
        ],
      },
      {
        path: "reserve",
        redirect: "/reserve/dashboard",
        component: RouterView,
        children: [
          {
            path: "dashboard",
            name: "backend-reserve-dashboard",
            component: VeganReserveDashboard,
          },
          {
            path: "reserveinfo",
            name: "backend-reserve-info",
            component: VeganReserveInfo,
          },
        ],
      },
      /*
      |--------------------------------------------------------------------------
      | Backend Blocks Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "blocks",
        redirect: "/blocks/styles",
        component: RouterView,
        children: [
          {
            path: "styles",
            name: "backend-blocks-styles",
            component: BackendBlocksStyles,
          },
          {
            path: "options",
            name: "backend-blocks-options",
            component: BackendBlocksOptions,
          },
          {
            path: "forms",
            name: "backend-blocks-forms",
            component: BackendBlocksForms,
          },
          {
            path: "themed",
            name: "backend-blocks-themed",
            component: BackendBlocksThemed,
          },
          {
            path: "api",
            name: "backend-blocks-api",
            component: BackendBlocksApi,
          },
        ],
      },

      /*
      |--------------------------------------------------------------------------
      | Backend Elements Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "elements",
        redirect: "/elements/grid",
        component: RouterView,
        children: [
          {
            path: "grid",
            name: "backend-elements-grid",
            component: BackendElementsGrid,
          },
          {
            path: "typography",
            name: "backend-elements-typography",
            component: BackendElementsTypography,
          },
          {
            path: "icons",
            name: "backend-elements-icons",
            component: BackendElementsIcons,
          },
          {
            path: "buttons",
            name: "backend-elements-buttons",
            component: BackendElementsButtons,
          },
          {
            path: "button-groups",
            name: "backend-elements-button-groups",
            component: BackendElementsButtonGroups,
          },
          {
            path: "dropdowns",
            name: "backend-elements-dropdowns",
            component: BackendElementsDropdowns,
          },
          {
            path: "tabs",
            name: "backend-elements-tabs",
            component: BackendElementsTabs,
          },
          {
            path: "navigation",
            name: "backend-elements-navigation",
            component: BackendElementsNavigation,
          },
          {
            path: "navigation-horizontal",
            name: "backend-elements-navigation-horizontal",
            component: BackendElementsNavigationHorizontal,
          },
          {
            path: "progress",
            name: "backend-elements-progress",
            component: BackendElementsProgress,
          },
          {
            path: "alerts",
            name: "backend-elements-alerts",
            component: BackendElementsAlerts,
          },
          {
            path: "tooltips",
            name: "backend-elements-tooltips",
            component: BackendElementsTooltips,
          },
          {
            path: "popovers",
            name: "backend-elements-popovers",
            component: BackendElementsPopovers,
          },
          {
            path: "modals",
            name: "backend-elements-modals",
            component: BackendElementsModals,
          },
          {
            path: "images-overlay",
            name: "backend-elements-images-overlay",
            component: BackendElementsImagesOverlay,
          },
          {
            path: "timeline",
            name: "backend-elements-timeline",
            component: BackendElementsTimeline,
          },
          {
            path: "ribbons",
            name: "backend-elements-ribbons",
            component: BackendElementsRibbons,
          },
          {
            path: "animations",
            name: "backend-elements-animations",
            component: BackendElementsAnimations,
          },
          {
            path: "color-themes",
            name: "backend-elements-color-themes",
            component: BackendElementsColorThemes,
          },
        ],
      },

      /*
      |--------------------------------------------------------------------------
      | Backend Plugins Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "tables",
        redirect: "/tables/styles",
        component: RouterView,
        children: [
          {
            path: "styles",
            name: "backend-tables-styles",
            component: BackendTablesStyles,
          },
          {
            path: "responsive",
            name: "backend-tables-responsive",
            component: BackendTablesResponsive,
          },
          {
            path: "helpers",
            name: "backend-tables-helpers",
            component: BackendTablesHelpers,
          },
        ],
      },

      /*
      |--------------------------------------------------------------------------
      | Backend Forms Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "forms",
        redirect: "/forms/elements",
        component: RouterView,
        children: [
          {
            path: "elements",
            name: "backend-form-elements",
            component: BackendFormsElements,
          },
          {
            path: "layouts",
            name: "backend-form-layouts",
            component: BackendFormsLayouts,
          },
          {
            path: "input-groups",
            name: "backend-form-input-groups",
            component: BackendFormsInputGroups,
          },
          {
            path: "plugins",
            name: "backend-form-plugins",
            component: BackendFormsPlugins,
          },
          {
            path: "editors",
            name: "backend-form-editors",
            component: BackendFormsEditors,
          },
          {
            path: "validation",
            name: "backend-form-validation",
            component: BackendFormsValidation,
          },
        ],
      },

      /*
      |--------------------------------------------------------------------------
      | Backend Plugins Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "plugins",
        redirect: "/plugins/image-cropper",
        component: RouterView,
        children: [
          {
            path: "image-cropper",
            name: "backend-plugins-image-cropper",
            component: BackendPluginsImageCropper,
          },
          {
            path: "charts",
            name: "backend-plugins-charts",
            component: BackendPluginsCharts,
          },
          {
            path: "calendar",
            name: "backend-plugins-calendar",
            component: BackendPluginsCalendar,
          },
          {
            path: "carousel",
            name: "backend-plugins-carousel",
            component: BackendPluginsCarousel,
          },
          {
            path: "syntax-highlighting",
            name: "backend-plugins-syntax-highlighting",
            component: BackendPluginsSyntaxHighlighting,
          },
          {
            path: "rating",
            name: "backend-plugins-rating",
            component: BackendPluginsRating,
          },
          {
            path: "dialogs",
            name: "backend-plugins-dialogs",
            component: BackendPluginsDialogs,
          },
          {
            path: "notifications",
            name: "backend-plugins-notifications",
            component: BackendPluginsNotifications,
          },
          {
            path: "gallery",
            name: "backend-plugins-gallery",
            component: BackendPluginsGallery,
          },
        ],
      },

      /*
      |--------------------------------------------------------------------------
      | Backend Layout Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "layout",
        redirect: "/layout/api",
        component: RouterView,
        children: [
          {
            path: "page/default",
            name: "backend-layout-page-default",
            component: BackendLayoutPageDefault,
          },
          {
            path: "page/flipped",
            name: "backend-layout-page-flipped",
            component: BackendLayoutPageFlipped,
          },
          {
            path: "dark-mode/on",
            name: "backend-layout-dark-mode-on",
            component: BackendLayoutDarkModeOn,
          },
          {
            path: "dark-mode/off",
            name: "backend-layout-dark-mode-off",
            component: BackendLayoutDarkModeOff,
          },
          {
            path: "dark-mode/system",
            name: "backend-layout-dark-mode-system",
            component: BackendLayoutDarkModeSystem,
          },
          {
            path: "main-content/full-width",
            name: "backend-layout-main-content-full-width",
            component: BackendLayoutMainContentFullWidth,
          },
          {
            path: "main-content/narrow",
            name: "backend-layout-main-content-narrow",
            component: BackendLayoutMainContentNarrow,
          },
          {
            path: "main-content/boxed",
            name: "backend-layout-main-content-boxed",
            component: BackendLayoutMainContentBoxed,
          },
          {
            path: "header/fixed-light",
            name: "backend-layout-header-fixed-light",
            component: BackendLayoutHeaderFixedLight,
          },
          {
            path: "header/fixed-dark",
            name: "backend-layout-header-fixed-dark",
            component: BackendLayoutHeaderFixedDark,
          },
          {
            path: "header/static-light",
            name: "backend-layout-header-static-light",
            component: BackendLayoutHeaderStaticLight,
          },
          {
            path: "header/static-dark",
            name: "backend-layout-header-static-dark",
            component: BackendLayoutHeaderStaticDark,
          },
          {
            path: "sidebar/mini",
            name: "backend-layout-sidebar-mini",
            component: BackendLayoutSidebarMini,
          },
          {
            path: "sidebar/light",
            name: "backend-layout-sidebar-light",
            component: BackendLayoutSidebarLight,
          },
          {
            path: "sidebar/dark",
            name: "backend-layout-sidebar-dark",
            component: BackendLayoutSidebarDark,
          },
          {
            path: "sidebar/hidden",
            name: "backend-layout-sidebar-hidden",
            component: BackendLayoutSidebarHidden,
          },
          {
            path: "side-overlay/visible",
            name: "backend-layout-side-overlay-visible",
            component: BackendLayoutSideOverlayVisible,
          },
          {
            path: "side-overlay/hover-mode",
            name: "backend-layout-side-overlay-hover-mode",
            component: BackendLayoutSideOverlayHoverMode,
          },
          {
            path: "side-overlay/no-page-overlay",
            name: "backend-layout-side-overlay-no-page-overlay",
            component: BackendLayoutSideOverlayNoPageOverlay,
          },
          {
            path: "loaders",
            name: "backend-layout-loaders",
            component: BackendLayoutLoaders,
          },
          {
            path: "api",
            name: "backend-layout-api",
            component: BackendLayoutApi,
          },
        ],
      },

      /*
      |--------------------------------------------------------------------------
      | Backend Generic Pages Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "pages/generic",
        redirect: "/pages/generic/blank",
        component: RouterView,
        children: [
          {
            path: "blank",
            name: "backend-pages-generic-blank",
            component: BackendPagesGenericBlank,
          },
          {
            path: "blank-block",
            name: "backend-pages-generic-blank-block",
            component: BackendPagesGenericBlankBlock,
          },
          {
            path: "search",
            name: "backend-pages-generic-search",
            component: BackendPagesGenericSearch,
          },
          {
            path: "profile",
            name: "backend-pages-generic-profile",
            component: BackendPagesGenericProfile,
          },
          {
            path: "invoice",
            name: "backend-pages-generic-invoice",
            component: BackendPagesGenericInvoice,
          },
          {
            path: "pricing-plans",
            name: "backend-pages-generic-pricing-plans",
            component: BackendPagesGenericPricingPlans,
          },
          {
            path: "faq",
            name: "backend-pages-generic-faq",
            component: BackendPagesGenericFaq,
          },
          {
            path: "inbox",
            name: "backend-pages-generic-inbox",
            component: BackendPagesGenericInbox,
          },
        ],
      },

      /*
      |--------------------------------------------------------------------------
      | Backend Pages Routes
      |--------------------------------------------------------------------------
      */
      {
        path: "pages",
        component: RouterView,
        children: [
          {
            path: "auth",
            name: "backend-pages-auth",
            component: BackendPagesAuth,
          },
          {
            path: "errors",
            name: "backend-pages-errors",
            component: BackendPagesErrors,
          },
        ],
      },
    ],
  },

  /*
  |
  |--------------------------------------------------------------------------
  | Special Routes
  |--------------------------------------------------------------------------
  |
  */
  {
    path: "/specials",
    component: LayoutSimple,
    children: [
      {
        path: "maintenance",
        name: "specials-maintenance",
        component: SpecialsMaintenance,
      },
      {
        path: "status",
        name: "specials-status",
        component: SpecialsStatus,
      },
      {
        path: "coming-soon",
        name: "specials-coming-soon",
        component: SpecialsComingSoon,
      },
    ],
  },

  /*
  |
  |--------------------------------------------------------------------------
  | Auth Routes
  |--------------------------------------------------------------------------
  |
  */
  {
    path: "/auth",
    component: LayoutSimple,
    children: [
      {
        path: "signin",
        name: "auth-signin",
        component: AuthSignIn,
      },
      {
        path: "signin2",
        name: "auth-signin2",
        component: AuthSignIn2,
      },
      {
        path: "signin3",
        name: "auth-signin3",
        component: AuthSignIn3,
      },
      {
        path: "signup",
        name: "auth-signup",
        component: AuthSignUp,
      },
      {
        path: "signup2",
        name: "auth-signup2",
        component: AuthSignUp2,
      },
      {
        path: "signup3",
        name: "auth-signup3",
        component: AuthSignUp3,
      },
      {
        path: "lock",
        name: "auth-lock",
        component: AuthLock,
      },
      {
        path: "lock2",
        name: "auth-lock2",
        component: AuthLock2,
      },
      {
        path: "lock3",
        name: "auth-lock3",
        component: AuthLock3,
      },
      {
        path: "reminder",
        name: "auth-reminder",
        component: AuthReminder,
      },
      {
        path: "reminder2",
        name: "auth-reminder2",
        component: AuthReminder2,
      },
      {
        path: "reminder3",
        name: "auth-reminder3",
        component: AuthReminder3,
      },
    ],
  },

  /*
  |
  |--------------------------------------------------------------------------
  | Error Routes
  |--------------------------------------------------------------------------
  |
  */
  {
    path: "/errors",
    component: LayoutSimple,
    children: [
      {
        path: "400",
        name: "error-400",
        component: Error400,
      },
      {
        path: "401",
        name: "error-401",
        component: Error401,
      },
      {
        path: "403",
        name: "error-403",
        component: Error403,
      },
      {
        path: "404",
        name: "error-404",
        component: Error404,
      },
      {
        path: "500",
        name: "error-500",
        component: Error500,
      },
      {
        path: "503",
        name: "error-503",
        component: Error503,
      },
    ],
  },
  {
    path: "/business/backend",
    redirect: "/business/backend/dashboard",
    component: BLayoutBackend,
    children: [
      {
        path: "dashboard",
        name: "business-backend-dashboard",
        component: BusinessBackend,
      },
      {
        path: "/profile",
        name: "business-backend-profile",
        component: BusinessProfileView,
      },
      {
        path: "/reserve",
        name: "business-backend-reserve",
        component: BusinessCalendar,
      },
    ],
  },
];

// Create Router
const router = createRouter({
  history: createWebHashHistory(),
  linkActiveClass: "active",
  linkExactActiveClass: "",
  scrollBehavior() {
    return { left: 0, top: 0 };
  },
  routes,
});

// NProgress
/*eslint-disable no-unused-vars*/
NProgress.configure({ showSpinner: false });

router.beforeResolve((to, from, next) => {
  NProgress.start();
  next();
});

router.afterEach((to, from) => {
  NProgress.done();
});
/*eslint-enable no-unused-vars*/

export default router;

router.beforeEach((to, from) => {
  const admin = localStorage.getItem("access-admin"); //取admin 登入資訊
  const business = sessionStorage.getItem("access-business"); //business 登入資訊
  const user = localStorage.getItem("access-user"); //user 登入資訊
  const isLogin = admin || business || user; //若有取得到1種就表示有登入

  //如果去的元件位置 跟 來的元件位置是一樣的 ， 那就跳轉到一個專屬跳轉的空頁面
  if (to.name === from.name) {
    return { name: "blank" };
  }

  //如果去的頁面是空的， 用router 刷新頁面
  if (to.name === "blank") {
    router.go(0);
  }

  if (
    !isLogin &&
    to.name !== "login" &&
    to.name !== "index" &&
    to.name !== "userRegister" &&
    to.name !== "Forum-index" &&
    to.name !== "postIndex" &&
    to.name !== "postPage" &&
    to.name !== "BusinessRegister" &&
    to.name !== "restaurantIndex" &&
    to.name !== "restaurant-details" &&
    to.name !== "restaurant-reserve" &&
    to.name !== "restaurant-reserve-check" &&
    to.name !== "shoppingCart" &&
    to.name !== "Verification"
  ) {
    return { name: "login" };
  }
});
